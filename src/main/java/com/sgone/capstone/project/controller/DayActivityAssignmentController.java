package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DayActivityAssignmentController {

    private DayActivityAssignmentRepository dayActivityAssignmentRepository;

    public DayActivityAssignmentController(DayActivityAssignmentRepository dayActivityAssignmentRepository) {
        this.dayActivityAssignmentRepository = dayActivityAssignmentRepository;
    }

    @DeleteMapping("/deleteUserFromActivity")
    void deleteUserFromActivity(@RequestParam Long userID, @RequestParam Long dayActivityID) {
        dayActivityAssignmentRepository.deleteUserFromActivity(userID, dayActivityID);
        dayActivityAssignmentRepository.SplitCostEvenly(dayActivityID);
    }


}
