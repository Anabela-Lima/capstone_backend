package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.model.PayeeAndPayer;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import com.sgone.capstone.project.repository.DayActivityRepository;
import com.sgone.capstone.project.service.DayActivityAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class DayActivityAssignmentController {

    private DayActivityAssignmentRepository dayActivityAssignmentRepository;
    private DayActivityRepository dayActivityRepository;
    private DayActivityAssignmentService dayActivityAssignmentService;

    public DayActivityAssignmentController(DayActivityAssignmentRepository dayActivityAssignmentRepository,
                                           DayActivityRepository dayActivityRepository,
                                           DayActivityAssignmentService dayActivityAssignmentService) {
        this.dayActivityAssignmentRepository = dayActivityAssignmentRepository;
        this.dayActivityRepository = dayActivityRepository;
        this.dayActivityAssignmentService = dayActivityAssignmentService;
    }

    @GetMapping("/dayActivityAssignments")
    public ResponseEntity<List<DayActivityAssignment>> getActivityAssignments() {
        List<DayActivityAssignment> dayActivityAssignments = dayActivityAssignmentRepository.findAll();
        return ResponseEntity.ok().body(dayActivityAssignments);
    }

    @DeleteMapping("/deleteUserFromActivity")
    void deleteUserFromActivity(@RequestParam Long userID, @RequestParam Long dayActivityID) {
        dayActivityAssignmentRepository.deleteUserFromActivity(userID, dayActivityID);
        dayActivityAssignmentRepository.SplitCostEvenly(dayActivityID);
    }

    @PutMapping("/changePaymentOfDayActivity")
    void changPaymentOfDayActivity(@RequestParam Long userID, @RequestParam Long dayActivityID,
                                   @RequestParam Double paid, @RequestParam Double shouldPay) {
        dayActivityAssignmentRepository.changeActivityAssignmentRow(userID, dayActivityID, paid, shouldPay);
    }

    @GetMapping("/canSubmitActivityPaymentForm")
    public Map<String, Boolean> canSubmitPaymentForm(@RequestParam Long dayActivityID) {
        Double activityPrice = dayActivityRepository.getById(dayActivityID).getPrice();
        Double paid = dayActivityAssignmentRepository.getPaidTotalByActivity(dayActivityID);
        Double shouldPay = dayActivityAssignmentRepository.getShouldPayTotalByActivity(dayActivityID);
        if (Math.round(activityPrice) == Math.round(paid) && Math.round(activityPrice) == Math.round(shouldPay)) {
            return Collections.singletonMap("success", true);
        }

        return Collections.singletonMap("success", false);
    }

    @GetMapping("/generateOwingFromTrip")
    public List<PayeeAndPayer> generateTripCosts(@RequestParam Long tripID) {

        List<PayeeAndPayer> oweListWithoutCancellations = new ArrayList<>();
        List<PayeeAndPayer> oweListWithoutRepeats = new ArrayList<>();
        List<PayeeAndPayer> oweList = new ArrayList<>();

        List<DayActivityAssignment> dayActivitiesByTrip = dayActivityAssignmentRepository
                .getActivityAssignmentsByTripID(tripID);

        for (DayActivityAssignment dayActivityAssignment : dayActivitiesByTrip) {
            oweListWithoutCancellations.addAll(
                    dayActivityAssignmentService.generateActivityCostByUser(
                            dayActivityAssignment.getApplicationUser().getId(),
                            dayActivityAssignment.getDayActivity().getId())
            );
        }

        // add up all payments with same payee and payer

        for (int i=0; i < oweListWithoutCancellations.size();i++) {

            PayeeAndPayer payeeAndPayer = oweListWithoutCancellations.get(i);

            Long total = payeeAndPayer.getOwed();
            int indexOfSamePayeeAndPayer = dayActivityAssignmentService
                    .findIndexOfSamePayeeAndPayer(oweListWithoutCancellations,
                            payeeAndPayer.getPayee(), payeeAndPayer.getPayer(),
                            i);

            int j=0;
            while (indexOfSamePayeeAndPayer != -1 && j<10) {
                PayeeAndPayer samePayeeAndPayer = oweListWithoutCancellations.get(indexOfSamePayeeAndPayer);
                total += samePayeeAndPayer.getOwed();
                indexOfSamePayeeAndPayer = dayActivityAssignmentService
                        .findIndexOfSamePayeeAndPayer(oweListWithoutCancellations,
                                payeeAndPayer.getPayee(), payeeAndPayer.getPayer(),
                                i);
                j++;
            }

            payeeAndPayer.setOwed(total);
        }

        // cancel out money owed both ways

            for (int i=0; i < oweListWithoutCancellations.size(); i++){

                PayeeAndPayer payeeAndPayer = oweListWithoutCancellations.get(i);

                int indexOfInverse = dayActivityAssignmentService.findIndexOfInversePayeeAndPayer(
                    oweListWithoutCancellations, payeeAndPayer.getPayee(), payeeAndPayer.getPayer());
                if (indexOfInverse != -1) {
                    PayeeAndPayer inversePayeeAndPayer = oweListWithoutCancellations.get(indexOfInverse);
                    if (payeeAndPayer.getOwed() > inversePayeeAndPayer.getOwed()  && indexOfInverse > i) {
                        oweList.add(new PayeeAndPayer(payeeAndPayer.getPayee(),
                            payeeAndPayer.getPayer(), payeeAndPayer.getOwed() -
                            oweListWithoutCancellations.get(indexOfInverse).getOwed()));
                    } else if (payeeAndPayer.getOwed() < inversePayeeAndPayer.getOwed()  && indexOfInverse > i) {
                        oweList.add(new PayeeAndPayer(payeeAndPayer.getPayer(), payeeAndPayer.getPayee(),
                            inversePayeeAndPayer.getOwed() - payeeAndPayer.getOwed()));
                    }
                 } else {
                    oweList.add(new PayeeAndPayer(payeeAndPayer.getPayee(), payeeAndPayer.getPayer(),
                        payeeAndPayer.getOwed()));
                }
            }
        return oweList;

    }


}
