package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.controller.DayActivityController;
import com.sgone.capstone.project.model.DayActivity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DayActivityRepositoryTest {

    @Autowired
    DayActivityRepository dayActivityRepository;

    @Autowired
    DayActivity dayActivity;

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
    public void canDeleteDayActivity(Long dayActivityId){
        boolean exists = dayActivityRepository.existsById(dayActivityId);
        if(!exists){
            throw new IllegalStateException(
                    "Day ID of " + dayActivityId + " does not exist"
            );
        }
    }

}
