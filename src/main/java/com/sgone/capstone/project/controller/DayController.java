package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomDayDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.service.DayService;
import com.sgone.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day")         // first forward slash of url
public class DayController {


    @Autowired
    DayService dayService;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserService userService;



    // get day by id


    @GetMapping("/day/{id}")
    public ResponseEntity<StandardResponseDto<CustomDayDto>> getDay (@PathVariable Long id ) {

        Day day = dayService.getDayById(id).get();                  // getting day
        String dayName = day.getName();                             // getting day Name

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto(
                        true,
                        "your day is" + dayName,
                         new CustomDayDto(
                                 day.getId(),
                                 day.getName(),
                                 day.getBudget(),
                                 day.getDate()
                         )
                ));
    }

    // get all days asssociated with a trip

    @GetMapping("/days")

    public ResponseEntity<List<CustomDayDto>> getAllDays (String tripcode) {

        // get all days using tripcode

        List<CustomDayDto> daysIntrip =  userService.getAllDaysByTripCode(tripcode);

        String tripName = tripRepository.findByTripCode(tripcode).get().getName();

        return (ResponseEntity.ok().body(daysIntrip));
    }




    // update day details









    // delete a day



}
