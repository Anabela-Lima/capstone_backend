package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomDayDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.DayActivity;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import com.sgone.capstone.project.repository.DayActivityRepository;
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
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    DayActivityAssignmentRepository dayActivityAssignmentRepository;

    @Autowired
    DayActivityRepository dayActivityRepository;




    // get day by id


    @PutMapping("/changeBudget")
    void changeBudget(@RequestParam Long dayID, @RequestParam Double budget) {
        dayRepository.changeBudget(dayID, budget);
    }

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


    // delete a day

    @DeleteMapping("/delete")
    public ResponseEntity<String> cancelDay(
            @RequestParam(required = true) Long id



    ){


           /*

           1. Get day object to be deleted using the id

           */

        Optional<Day> dayToBeDeleted = dayService.getDayById(id);

        // get daytobedeleted id

        Long dayId= dayToBeDeleted.get().getId();

        // get all activities belonging to that day

        List<DayActivity> activitiesForDayToBeDeleted = dayActivityRepository.getAllDayActivityByDayId(id);


        // create dayActivity array of long

        List<Long> dayActivityIds = activitiesForDayToBeDeleted
                .stream()
                        .map(dayActivity -> {

                            return( dayActivity.getId());

                        }).collect(Collectors.toList());


        // for loop to loop through dayActivityids

        for(int i=0 ; i<dayActivityIds.size(); i++){                        // list of activity ids

            dayActivityAssignmentRepository.deleteByDayActivityId(dayActivityIds.get(i));

        }


         // remove users assigned to the activities

         dayActivityRepository.removeByDayId(id);

         // remove day from day table
        dayRepository.deleteDayById(id);




        return (ResponseEntity.ok("Day removed"));
    }



    // put Method - update day details

    @PutMapping("/updateDayDetails")
    ResponseEntity<String > updateDayDetails (@RequestParam Long dayId,
                           @RequestParam String name,
                           @RequestParam Double budget,
                           @DateTimeFormat(pattern= "yyyy-MM-dd HH:mm:ss")   @RequestParam LocalDateTime date)

    {
        //get day using Long id

       Optional<Day> dayTobeUpdated = dayService.getDayById(dayId);


       dayRepository.updateDay( dayId, name,budget, date);


       return( ResponseEntity.ok().body("your day has been updated"));

    }


    @GetMapping("/portionOfDayBudgetSpent")
    ResponseEntity<Map<String, Double>> portionOfDayBudgetSpent(@RequestParam Long dayID) {
        Double budget = dayRepository.getById(dayID).getBudget();
        Long spent = dayRepository.daySpend(dayID);
        if (budget != 0) {
            return ResponseEntity.ok().body(Collections.singletonMap("portionOfBudget", spent / budget));
        }

        return ResponseEntity.ok().body(Collections.singletonMap("portionOfBudget", 0.0));
    }


    @GetMapping("/getActivitiesByDayID")
    ResponseEntity<List<DayActivity>> getActivitiesByDayId(@RequestParam Long dayID) {
        List<DayActivity> allActivities = dayActivityRepository.findAll();
        List<DayActivity> activities = new ArrayList<>();

        for (DayActivity dayActivity : allActivities) {
            if (dayActivity.getDay().getId() == dayID) {
                activities.add(dayActivity);
            }
        }

        return ResponseEntity.ok().body(activities);
    }

    @GetMapping("/getDaysByTrip")
    ResponseEntity<List<Day>> getDaysByTrip(@RequestParam Long tripID) {
        List<Day> days = dayRepository.getAllDaysByTripId(tripID);
        return ResponseEntity.ok().body(days);
    }


}
