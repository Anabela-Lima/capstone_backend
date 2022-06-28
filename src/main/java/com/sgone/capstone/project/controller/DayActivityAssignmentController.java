package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import com.sgone.capstone.project.repository.DayActivityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
