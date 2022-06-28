package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import com.sgone.capstone.project.repository.DayActivityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class DayActivityAssignmentController {

    private DayActivityAssignmentRepository dayActivityAssignmentRepository;
    private DayActivityRepository dayActivityRepository;

    public DayActivityAssignmentController(DayActivityAssignmentRepository dayActivityAssignmentRepository, DayActivityRepository dayActivityRepository) {
        this.dayActivityAssignmentRepository = dayActivityAssignmentRepository;
        this.dayActivityRepository = dayActivityRepository;
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

    @GetMapping("/generateTripCostByUserAndTrip")
    public List<Map<Long, Long>> generateTripCostByUser(@RequestParam Long userID,
                                                          @RequestParam Long tripID) {
        List<Map<Long, Long>> userOwesList = new ArrayList<>();

        List<DayActivityAssignment> dayActivityAssignments = dayActivityAssignmentRepository
                .getActivityAssignmentsByTripID(tripID);

        Double paid = dayActivityAssignmentRepository.getTotalPaidOfUserFromTrip(tripID,
                userID);

        Double shouldPay = dayActivityAssignmentRepository.getTotalShouldPayOfUserFromTrip(tripID,
                userID);

        Long remainsToPay;
        Long userHasPaidOver;

        remainsToPay = Math.round(shouldPay) - Math.round(paid) ;

        for (DayActivityAssignment dayActivityAssignment : dayActivityAssignments) {
            if (remainsToPay > 0 && dayActivityAssignment.getApplicationUser().getId() != userID ) {
                userHasPaidOver = Math.round(dayActivityAssignment.getPaid()) -
                        Math.round(dayActivityAssignment.getShouldPay());
                if ((remainsToPay - userHasPaidOver) >= 0) {
                    userOwesList.add(Collections.singletonMap(dayActivityAssignment.getApplicationUser().getId(),
                            userHasPaidOver));
                    dayActivityAssignmentRepository.changeActivityAssignmentRow(dayActivityAssignment
                            .getApplicationUser().getId(), dayActivityAssignment.getDayActivity().getId(),
                            dayActivityAssignment.getShouldPay(), dayActivityAssignment.getShouldPay());
                    remainsToPay = remainsToPay - userHasPaidOver;
                } else {
                    userOwesList.add(Collections.singletonMap(dayActivityAssignment.getApplicationUser().getId(),
                            remainsToPay));
                    dayActivityAssignmentRepository.changeActivityAssignmentRow(dayActivityAssignment
                            .getApplicationUser().getId(), dayActivityAssignment.getDayActivity().getId(),
                            dayActivityAssignment.getPaid() - remainsToPay,
                            dayActivityAssignment.getShouldPay());
                    remainsToPay = 0l;
                }
            }
        }

        return userOwesList;
    }


}
