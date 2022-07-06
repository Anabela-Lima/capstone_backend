package com.sgone.capstone.project.service;

import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DayActivityAssignmentServiceTest {


@Autowired
    DayActivityAssignmentRepository dayActivityAssignmentRepository;


@Test
    void contextLoads(){}

    @Test
    public void canDeleteUserFromActivity(){

    Day test = new Day(26L, "Test");
    DayActivityAssignment testing = new DayActivityAssignment(43L);
    dayActivityAssignmentRepository.deleteUserFromActivity(26L,43L);
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
