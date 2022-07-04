package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomApplicationUserDto;
import com.sgone.capstone.dto.CustomTripDto;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.*;
import com.sgone.capstone.project.repository.*;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.repository.TripRepository;
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
@RequestMapping("/user")
@CrossOrigin(value = "*", methods = RequestMethod.GET)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
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

    public UserController() {}

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
    public ResponseEntity<List<Trip>> getTrips() {
        List<Trip> trips = userService.getAllTrips();
        return ResponseEntity.status(HttpStatus.OK).body(trips);
    }


    @PostMapping("/trip/new")
    public ResponseEntity<StandardResponseDto<?>> createTrip(
            @RequestParam(required = true, defaultValue = "7") Long userId,
            @RequestParam(required = true, defaultValue = "girls trip") String name,
            @RequestParam(required = true, defaultValue = "Morocco") String country,
            @RequestParam(required = true, defaultValue = "girls trip in Morocco, let's party!") String description,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(required = true, defaultValue = "2022-06-28 14:01:25") LocalDateTime startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(required = true, defaultValue = "2022-06-30 14:01:25") LocalDateTime endDate
    ) {
        /*
            1. we get userId from the frontend
            2. we get trip information from the frontend
            3. get the user who created the trip from database using userId
            4. create the trip
            5. assign the user we just got to the newly created trip
        */
        NewTripDto newTripDto = new NewTripDto(
                userId,
                name,
                country,
                description,
                startDate,
                endDate
        );

        try {
            CustomTripDto newTrip = userService.createTrip(newTripDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new StandardResponseDto<>(
                            true,
                            "New trip created.",
                            newTrip
                    ));
        }
        catch (RuntimeException re) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponseDto<>(
                            false,
                            re.getMessage(),
                            null
                    ));
        }
    }


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

    // Search user by first name and last name
    @GetMapping("/getUserByName/{firstname}/{lastname}")
    public ResponseEntity<List<CustomApplicationUserDto>> getUserByName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) throws Exception {
        List<ApplicationUser> users = userRepository.findAll();
        List<CustomApplicationUserDto> usersNameKeyword = users
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
                .filter(s -> s.getFirstname().contains(firstname)).collect(Collectors.toList())
                .stream().filter(s -> s.getLastname().contains(lastname)).collect(Collectors.toList());
            if (usersNameKeyword.equals(Collections.emptyList())) {
                throw new Exception("User does not exist.");
            }
        return ResponseEntity.status(HttpStatus.OK).body(usersNameKeyword);
    }


    // Search user by username
    @GetMapping("/getUserByUserName/{username}")
    public ResponseEntity<List<CustomApplicationUserDto>> getUserByUserName(@PathVariable("username") String username) throws Exception {
        List<ApplicationUser> users = userRepository.findAll();
        List<CustomApplicationUserDto> usersNameKeyword = users
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
                .filter(s -> s.getUsername().contains(username)).collect(Collectors.toList());
        if (usersNameKeyword.equals(Collections.emptyList())) {
            throw new Exception("That Username you entered does not exist. Please enter a valid username");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersNameKeyword);
    }

    // delete and/or cancel trip
    @DeleteMapping("/trip/cancel_trip")
    public ResponseEntity<StandardResponseDto<?>> cancelTrip(
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
        List<DayActivity> allDayActivitiesBelongingToTrip = new ArrayList<>();

        /*
            7. Loop through day IDs array to get activities for each day
         */
        for (Long dayId: allDayIdsBelongToTrip) {
            List<DayActivity> activitiesBelongingToDay =
                    dayActivityRepository.getAllDayActivityByDayId(dayId);

            /*
                8. Add current day activities to all activities belonging to trip
             */
            allDayActivitiesBelongingToTrip.addAll(activitiesBelongingToDay);
        }

        /*
            10. Get all activities IDs from all activities array
         */
        List<Long> allDayActivityIds = allDayActivitiesBelongingToTrip
                .stream()
                .map(dayActivity -> {
                    return dayActivity.getId();
                })
                .collect(Collectors.toList());

        /*
            11. Remove activities assignments, removing all users assigned
                to all activities

                (Clearing activity_assignment table data)
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

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto<>(
                        true,
                        String.format(
                                "Trip %s to %s has been cancelled.",
                                tripToBeDeleted.getName(),
                                tripToBeDeleted.getCountry()
                        ),
                        null
                ));
    }


    @GetMapping("/OrganisedTrips")
    ResponseEntity<List<Trip>> getOrganisedTrips(@RequestParam Long userID) {
        List<Long> allTripsByUser = tripAssignmentRepository.returnAllTripsByUser(userID);
        List<Trip> allTripsOrganisedByUser = new ArrayList<>();

        for (Long tripID : allTripsByUser) {
            if (userID == tripAssignmentRepository.returnOrganiserOfTrip(tripID)) {
                allTripsOrganisedByUser.add(tripRepository.findById(tripID)
                        .orElseThrow());
            }
        }

        return ResponseEntity.ok().body(allTripsOrganisedByUser);

    }









}





