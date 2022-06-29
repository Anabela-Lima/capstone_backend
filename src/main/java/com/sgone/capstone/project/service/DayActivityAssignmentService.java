package com.sgone.capstone.project.service;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DayActivityAssignmentService {

    private DayActivityAssignmentRepository dayActivityAssignmentRepository;

    public DayActivityAssignmentService(DayActivityAssignmentRepository dayActivityAssignmentRepository) {
        this.dayActivityAssignmentRepository = dayActivityAssignmentRepository;
    }

    public List<Map<Map<Long, Long>, Long>> generateActivityCostByUser(Long userID, Long dayActivityID) {
        List<Map<Map<Long, Long>, Long>> userOwesList = new ArrayList<>();

        List<DayActivityAssignment> dayActivityAssignments = dayActivityAssignmentRepository
                .returnActivityAssignmentsByActivityID(dayActivityID);

        Double paid = dayActivityAssignmentRepository.userPaidForCertainActivity(dayActivityID, userID);

        Double shouldPay = dayActivityAssignmentRepository.userShouldPayForCertainActivity(dayActivityID, userID);

        Long remainsToPay;
        Long userHasPaidOver;

        remainsToPay = Math.round(shouldPay) - Math.round(paid) ;

        for (DayActivityAssignment dayActivityAssignment : dayActivityAssignments) {
            userHasPaidOver = Math.round(dayActivityAssignment.getPaid()) -
                    Math.round(dayActivityAssignment.getShouldPay());
            if (remainsToPay > 0 && dayActivityAssignment.getApplicationUser().getId() != userID
                    && userHasPaidOver > 0) {
                if ((remainsToPay - userHasPaidOver) >= 0) {
                    userOwesList.add(Collections.singletonMap(
                            Collections.singletonMap(dayActivityAssignment.getApplicationUser().getId(),
                                    userID),
                            userHasPaidOver));
                    dayActivityAssignmentRepository.changeActivityAssignmentRow(dayActivityAssignment
                                    .getApplicationUser().getId(), dayActivityAssignment.getDayActivity().getId(),
                            dayActivityAssignment.getShouldPay(), dayActivityAssignment.getShouldPay());
                    dayActivityAssignmentRepository.changeActivityAssignmentRow(userID, dayActivityID,
                            dayActivityAssignmentRepository.userPaidForCertainActivity(dayActivityID, userID)
                                    + userHasPaidOver, dayActivityAssignmentRepository
                                    .userShouldPayForCertainActivity(dayActivityID, userID));
                    remainsToPay = remainsToPay - userHasPaidOver;
                } else {
                    userOwesList.add(Collections.singletonMap(
                            Collections.singletonMap(dayActivityAssignment.getApplicationUser().getId(),
                                    userID),
                            remainsToPay));
                    dayActivityAssignmentRepository.changeActivityAssignmentRow(dayActivityAssignment
                                    .getApplicationUser().getId(), dayActivityAssignment.getDayActivity().getId(),
                            dayActivityAssignment.getPaid() - remainsToPay,
                            dayActivityAssignment.getShouldPay());
                    dayActivityAssignmentRepository.changeActivityAssignmentRow(userID, dayActivityID,
                            dayActivityAssignmentRepository
                                    .userShouldPayForCertainActivity(dayActivityID, userID),
                            dayActivityAssignmentRepository
                                    .userShouldPayForCertainActivity(dayActivityID, userID));
                    remainsToPay = 0l;
                }
            }
        }

        return userOwesList;
    }
}
