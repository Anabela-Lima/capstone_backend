package com.sgone.capstone.project.service;


import com.sgone.capstone.dto.CustomDayDto;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.controller.DayController;
import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.DayRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DayServiceTest {

@Autowired
    DayController dayController;

@Autowired
    DayRepository dayRepository;

@Autowired
DayServiceTest dayServiceTest;

@Autowired
UserService userService;

@Autowired
Trip trip;

@Autowired
Day day;

@Test
    void contextLoads(){}

    @Test
    public void canFindById(){
        Optional<Day> found = dayRepository.findById(2L);
        assertThat(found.get().getId().equals(2));
    }

    @Test
    public void canUpdateDayDetails(Long dayId, String name, double budget, LocalDateTime date){

    Day day = dayRepository.findById(dayId).orElseThrow(() -> new IllegalStateException("Day ID of " + dayId+" does not exist"));
    if (day.getName() !=null && !Objects.equals(day.getName(),name)){
        day.setName(name);
        }
    if (day.getBudget() !=null && !Objects.equals(day.getBudget(),budget)){
        day.setBudget(budget);
        }
        if (day.getDate() !=null && !Objects.equals(day.getDate(),date)){
            day.setDate(date);
        }
    }

    @Test
    public void canAddDay(){
//        LocalDateTime startDate = LocalDateTime.of(2022, Month.SEPTEMBER, 19,16,00,00);
//        LocalDateTime endDate = LocalDateTime.of(2022, Month.SEPTEMBER, 29,18,00,00);
//incomplete

        Day day1 = new Day(day.getId(), day.getName(),day.getBudget(),day.getDate(),day.getTrip(),day.getDayActivities());
        Trip trip1 = new Trip(trip.getTripCode(),trip.getName(),trip.getStartDate(),trip.getEndDate(),trip.getDescription(),trip.getCountry());
        Trip daytrip = userService.getTrip(trip1.getTripCode());
       Day daysave = dayRepository.save(day1);



}

}
