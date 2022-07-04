package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.service.DayActivityAssignmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DayActivityAssignmentRepositoryTest {

    @Autowired
    DayActivityAssignmentService dayActivityAssignmentService;

    @Autowired
    DayActivityAssignmentRepository dayActivityAssignmentRepository;

    @Test
    void contextLoads(){}

    @Test
    public void canGenerateOwningFromTrip(){
        Optional<DayActivityAssignment> found = dayActivityAssignmentRepository.findById(2L);
        assertThat(found.get().getId().equals(2));
    }

}