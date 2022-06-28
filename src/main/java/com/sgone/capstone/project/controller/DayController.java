package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomDayDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.DayRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.service.DayService;
import com.sgone.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/day")         // first forward slash of url
public class DayController {


    @Autowired
    DayService dayService;

    @Autowired
    DayRepository dayRepository;

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

    // add a new day


    @PostMapping("/addDay")

    // annotated properties dont need to be requested because JPA will take care of it
    public ResponseEntity<CustomDayDto> addDay(
            @RequestParam String tripCode,
            @RequestParam  String name,
            @RequestParam Double budget,
            @RequestParam @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")LocalDateTime date)    // needed datetimeformat to format the date
    {

        Trip tripForDay = userService.getTrip(tripCode);

        Day day = new Day(name, budget, date,tripForDay );
        Day daysaved=  dayRepository.save(day);

        // custom day dto needs to have an id, so lets get it back and pass it in

       Long id = daysaved.getId();


        return (ResponseEntity.ok().body(

                new CustomDayDto(
                        daysaved.getId(),
                        daysaved.getName(),
                        daysaved.getBudget(),
                        daysaved.getDate()
                )
        ));


    }










}
