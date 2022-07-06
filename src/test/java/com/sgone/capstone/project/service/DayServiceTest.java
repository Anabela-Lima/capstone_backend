package com.sgone.capstone.project.service;


import com.sgone.capstone.dto.CustomDayDto;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.controller.DayController;
import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.DayActivity;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.DayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DayServiceTest {

@Autowired
DayController dayController;

@Autowired
DayRepository dayRepository;

@Test
    void contextLoads(){}

    @Test
    public void canFindById(){
        Optional<Day> found = dayRepository.findById(2L);
        assertThat(found.get().getId().equals(2));
    }

    @Test
    public void canUpdateDayDetails(){
    LocalDateTime startDate = LocalDateTime.of(2022, Month.SEPTEMBER, 19,16,00,00);
    dayRepository.updateDay(1L,"Test", 230.0, startDate);
    }


@Test
    public void canDeleteDay(){
    Day test = new Day(26L, "Test");
    dayRepository.save(test);
    dayRepository.deleteDayById(test.getId());

}

}
