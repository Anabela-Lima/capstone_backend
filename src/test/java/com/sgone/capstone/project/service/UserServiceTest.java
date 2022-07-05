package com.sgone.capstone.project.service;

import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.repository.DayRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import com.sgone.capstone.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BooleanSupplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    DayRepository dayRepository;
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;
    @Autowired
    NewTripDto newTripDto;
    @Autowired
    ApplicationUser applicationUser;
    @Autowired
    TripAssignment tripAssignment;

    @Test
    void contextLoads(){
    }

@Test
    public void canGetTrip(){
        //Given - list of ids available

        //When - trip code is inputted,
    userService.getTrip(newTripDto.getCountry());
    Optional<Trip> trip = tripRepository.findById(1L);
        //Then - generate the country of the trip
    assertEquals(trip.get().getCountry(),"Portugal");
}

@Test
    public void canAddNewTrip(){
    //incomplete
        userService.createTrip(newTripDto);
       LocalDateTime startDate = LocalDateTime.of(2022, Month.SEPTEMBER, 19,16,00,00);
    LocalDateTime endDate = LocalDateTime.of(2022, Month.SEPTEMBER, 29,18,00,00);
        newTripDto = new NewTripDto(9L,"Sport Tour","Honduras","Shown around history of sport", startDate, endDate);
    }

    @Test
    public void canAssignUser(){

    Optional<ApplicationUser> applicationUser1 = userRepository.getUser(1L);
    Optional<Trip> trip = tripRepository.findByTripCode(UUID.randomUUID().toString());


    assertNotNull(dayRepository.testQuery());
    }

    @Test
    public void canGetTrips(){
    assertTrue((BooleanSupplier) tripRepository.findAll());
    }








}
