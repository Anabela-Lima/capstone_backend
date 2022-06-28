package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomApplicationUserDto;
import com.sgone.capstone.dto.CustomTripDto;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.*;
import com.sgone.capstone.project.repository.*;
import com.sgone.capstone.dataloader.DataLoaderUsersArray;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.service.UserService;
import com.sgone.capstone.security.management.user.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserManagementRepository userManagementRepository;

    public UserController() {}

    @Autowired
    private UserRepository userRepository;
    public UserController(UserService userService, UserManagementRepository userManagementRepository) {
        this.userService = userService;
        this.userManagementRepository = userManagementRepository;
    }

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripAssignmentRepository tripAssignmentRepository;
    @Autowired
    private DayRepository dayRepository;
    @Autowired
    private DayActivityRepository dayActivityRepository;
    @Autowired
    private DayActivityAssignmentRepository dayActivityAssignmentRepository;


    // get trip by trip code
    // ? = wildcard and allows us to return whatever we placed inside diamond brackets
    @GetMapping("/trip")
    public ResponseEntity<StandardResponseDto<?>> getTrip(
            @RequestParam String tripCode
    ) {
        String tripName = userService.getTrip(tripCode).getName();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto<>(
                        true,
                        "Your trip is " + tripName ,
                        String.format("The  provided: %s", tripCode) // string interpolation
                ));
    }


    // get trips
    @GetMapping("/trips")
    public ResponseEntity<List<CustomTripDto>> getTrips() {
        List<Trip> trips = userService.getAllTrips();
        System.out.println(trips);
        List<CustomTripDto> customTripDtos = trips
                .stream()
                .map(trip -> {
                    return new CustomTripDto(
                            trip.getId(),
                            trip.getTripCode(),
                            trip.getName(),
                            trip.getStartDate(),
                            trip.getEndDate(),
                            trip.getDescription(),
                            trip.getCountry()
                    );
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customTripDtos);
    }


//    @PostMapping("/trip/new")
//    public ResponseEntity<StandardResponseDto<?>> createTrip(
//            @RequestParam(required = true) Long userId,
//            @RequestParam(required = true) String name,
//            @RequestParam(required = true) String country,
//            @RequestParam(required = true) String description,
//            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//            @RequestParam(required = true) LocalDateTime startDate,
//            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//            @RequestParam(required = true) LocalDateTime endDate
//    ) {
//        /*
//            1. we get userId from the frontend
//            2. we get trip information from the frontend
//            3. get the user who created the trip from database using userId
//            4. create the trip
//            5. assign the user we just got to the newly created trip
//        */
//        NewTripDto newTripDto = new NewTripDto(
//                userId,
//                name,
//                country,
//                description,
//                startDate,
//                endDate
//        );
//
//
//    }

    @PutMapping("/trips/assign_user")
    public ResponseEntity<Trip> assignUserToTrip(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) String tripCode
    ) {
        ApplicationUser user = userRepository.getUser(userId).get();
        Trip trip = tripRepository.findByTripCode(tripCode).get();

        TripAssignment newTripAssignment = new TripAssignment(
                trip,
                user
        );

        tripAssignmentRepository.save(newTripAssignment);

        return ResponseEntity.status(HttpStatus.OK).body(trip);
    }



    @PatchMapping("/trip/add_friend")
    public ResponseEntity<StandardResponseDto<?>> addFriendToTrip() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto<>(
                        false,
                        "Endpoint not yet implemented",
                        null
                ));
    }

    /**
     *  BELOW IS USING DTO TO PREVENT INFINITE JSON LOOP PROBLEM ;)
     * @return
     */
    @GetMapping("/get_all_users")
    public ResponseEntity<List<CustomApplicationUserDto>> getAllOfTheUsers(){
        List<ApplicationUser> users = userRepository.findAll();
        List<CustomApplicationUserDto> customUsers = users
                .stream()
                .map(user -> {
                    Set<TripAssignment> tripAssignments = user.getTripAssignments();
                    Set<Long> tripAssignmentId = tripAssignments
                            .stream()
                            .map(tripAssignment -> {
                                return tripAssignment.getId();
                            })
                            .collect(Collectors.toSet());
                    return new CustomApplicationUserDto(
                            user.getId(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail(),
                            user.getMobile(),
                            user.getFirstname(),
                            user.getLastname(),
                            tripAssignmentId
                    );
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customUsers);
    }



    @GetMapping("/getUserByName/{firstname}")
    public ResponseEntity<List<CustomApplicationUserDto>> getUserByName(@PathVariable("firstname") String firstname){
        List<ApplicationUser> users = userRepository.findAll();
        List<CustomApplicationUserDto> usersNamedKeyword = users
                .stream()
                .map(user -> {
                    Set<TripAssignment> tripAssignments = user.getTripAssignments();
                    Set<Long> tripAssignmentId = tripAssignments
                            .stream()
                            .map(tripAssignment -> {
                                return tripAssignment.getId();
                            })
                            .collect(Collectors.toSet());
                    return new CustomApplicationUserDto(
                            user.getId(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail(),
                            user.getMobile(),
                            user.getFirstname(),
                            user.getLastname(),
                            tripAssignmentId
                    );
                })
                .filter(s -> s.getFirstname().contains(firstname)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(usersNamedKeyword);
    }





    // delete and/or cancel trip
    @DeleteMapping("/trip/cancel_trip")
    public ResponseEntity<String> cancelTrip(
            @RequestParam(required = true) String tripCode
    ) {

        /*
             1. Get the trip object to be deleted
             2. Get the trip ID from trip object
         */
        Trip tripToBeDeleted = tripRepository.findByTripCode(tripCode).get();
        Long tripToBeDeletedId = tripToBeDeleted.getId();

        /*
            3. Delete the trip assignment, removing all users assigned to this trip
         */
        tripAssignmentRepository.deleteByTripId(tripToBeDeletedId);

        /*
            4. Get all days belonging to this trip
         */
        List<Day> allDaysBelongToTrip =
                dayRepository.getAllDaysByTripId(tripToBeDeletedId);

        /*
            5. Get all day IDs from all days array above
         */
        List<Long> allDayIdsBelongToTrip = allDaysBelongToTrip
                .stream()
                .map(day -> {
                    return day.getId();
                })
                .collect(Collectors.toList());

        /*
            6. Create new empty day activity array to store all activity belonging
                to this trip
         */
        List<DayActivity> dayActivities = new ArrayList<>();

        /*
            7. Loop through day IDs array to get activities for each day
         */
        for (Long dayId: allDayIdsBelongToTrip) {
            List<DayActivity> activities =
                    dayActivityRepository.getAllDayActivityByDayId(dayId);

            /*
                8. Add current day activity to all activities array from step 6
             */
            dayActivities.addAll(activities);
        }

        /*
            9. If there aren't any activities, then stop and clean Day and Trip table
         */
        if (dayActivities.isEmpty()) {
            // remove from Day Table
            // then remove from Trip Table
        }

        /*
            10. Get all activities IDs from all activities array
         */
        List<Long> allDayActivityIds = dayActivities
                .stream()
                .map(dayActivity -> {
                    return dayActivity.getId();
                })
                .collect(Collectors.toList());

        /*
            11. Remove activities assignments, removing all users assigned
                to all activities
         */
        for (Long dayActivityId: allDayActivityIds) {
            dayActivityAssignmentRepository.deleteByDayActivityId(dayActivityId);
        }

        /*
            12. Then remove all trip activities
         */
        for (Long dayId: allDayIdsBelongToTrip) {
            dayActivityRepository.removeByDayId(dayId);
        }

        /*
            13. Then remove all days belonging to trip
         */
        dayRepository.deleteByTripId(tripToBeDeletedId);

        /*
            14. Lastly, remove said trip from Trip table
         */
        tripRepository.cancelTrip(tripCode);


//        List<String> tripCodes = userService.getAllTrips().stream();

//        List<String> tripCodes = userService.getAllTrips()
//                .stream()
//                .map(trip -> {return trip.getTripCode();})
//                .filter(trip -> {return trip.equals(tripCode);})
//                .collect(Collectors.toList());
//
////        .map(TripDto::tripCode)                                          // create a list of tripcodes
//        if (!tripCodes.isEmpty()) {
//            tripRepository.cancelTrip(tripCode);
//            ResponseEntity<String> OUT = ResponseEntity.ok("Trip" + userService.getAllTrips()
//                    .stream()
//                    .map(Trip::getName).collect(Collectors.toList())
//                    + " has been cancelled.");
//            return OUT;
//        }
        return ResponseEntity.badRequest().build();
    }













}





