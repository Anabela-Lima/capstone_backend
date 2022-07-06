package com.sgone.capstone.project.service;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DayActivityAssignmentServiceTest {

@Autowired
    DayActivityAssignmentService dayActivityAssignmentService;

@Autowired
    DayActivityAssignment dayActivityAssignment;

@Autowired
    DayActivityAssignmentRepository dayActivityAssignmentRepository;


@Test
    void contextLoads(){}

    @Test
    public void canDeleteUserFromActivity(Long useriD, Long dayActivityId){

    boolean exists = dayActivityAssignmentRepository.existsById(useriD);
    boolean exists2 = dayActivityAssignmentRepository.existsById(dayActivityId);

    if(!exists && !exists2){
        throw new IllegalStateException(
                "User ID and DayActivity ID of " + useriD + " and " + dayActivityId + " respectively does not exist"
        );
    }
    dayActivityAssignmentRepository.deleteUserFromActivity(useriD, dayActivityId);
    }

//   @Test
//    public void canChangePaymentOfDayActivity(Long userId, Long dayActivityId, double paid, double shouldPay){
//    DayActivityAssignment activityAssignment = dayActivityAssignmentRepository.obtainAppUserIdAndDayActivityId(userId, dayActivityId);
//      if (activityAssignment.getPaid() != null && !Objects.equals(activityAssignment.getPaid(), paid)){
//          activityAssignment.setPaid(paid);
//      }
//        if (activityAssignment.getShouldPay() != null && !Objects.equals(activityAssignment.getShouldPay(),shouldPay)){
//            activityAssignment.setShouldPay(shouldPay);
//        }
//        else throw new IllegalStateException("ID entered does not exist");
//}



}
