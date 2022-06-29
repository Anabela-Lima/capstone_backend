package com.sgone.capstone.project.service;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.model.PayeeAndPayer;
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

    public List<PayeeAndPayer> generateActivityCostByUser(Long userID, Long dayActivityID) {
        List<DayActivityAssignment> dayActivityAssignments = dayActivityAssignmentRepository
                .returnActivityAssignmentsByActivityID(dayActivityID);

        List<PayeeAndPayer> userOwesList = new ArrayList<>();

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

                    userOwesList.add(new PayeeAndPayer(
                            dayActivityAssignment.getApplicationUser().getId(),
                            userID,
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


                    userOwesList.add(new PayeeAndPayer(
                            dayActivityAssignment.getApplicationUser().getId(),
                            userID,
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

    public int findIndexOfInversePayeeAndPayer(List<PayeeAndPayer> payeeAndPayerList,
                                               Long payeeID, Long payerID) {
        for (int i = 0; i < payeeAndPayerList.size(); i++) {
            PayeeAndPayer payeeAndPayer = payeeAndPayerList.get(i);
            if (payeeID == payeeAndPayer.getPayer() && payerID == payeeAndPayer.getPayee()) {
                return i;
            }
        }
        return -1;
    }

    public int findIndexOfSamePayeeAndPayer(List<PayeeAndPayer> payeeAndPayerList,
                                               Long payeeID, Long payerID,
                                            int indexMustBeLargerThan) {
        for (int i = 0; i < payeeAndPayerList.size(); i++) {
            PayeeAndPayer samePayeeAndPayer = payeeAndPayerList.get(i);
            if (payeeID == samePayeeAndPayer.getPayee() && payerID == samePayeeAndPayer.getPayer()
            && i > indexMustBeLargerThan) {
                return i;
            }
        }
        return -1;
    }
}

