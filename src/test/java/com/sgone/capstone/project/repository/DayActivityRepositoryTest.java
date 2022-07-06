package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.controller.DayActivityController;
import com.sgone.capstone.project.model.DayActivity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DayActivityRepositoryTest {

    @Autowired
    DayActivityRepository dayActivityRepository;



    @Autowired
    DayActivityController dayActivityController;

    @Test
    void contextLoads(){}

    @Test
    public void canFindDayActivityAssignmentsByDayActivityId(){
        Optional<DayActivity> found = dayActivityRepository.findById(2L);
        assertThat(found.get().getId().equals(2));
    }

    @Test
    public void canDeleteDayActivity(){
        DayActivity test = new DayActivity(26L,"Test");
        dayActivityRepository.save(test);
        dayActivityRepository.deleteDayActivity(26L);

    }

}
