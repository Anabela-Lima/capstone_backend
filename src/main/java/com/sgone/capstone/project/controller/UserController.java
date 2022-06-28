package com.sgone.capstone.project.controller;

import com.sgone.capstone.dataloader.DataLoaderUsersArray;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.service.UserService;
import com.sgone.capstone.security.management.user.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    UserManagementRepository userManagementRepository;

    public UserController() {}

    @Autowired
    public UserController(UserService userService, UserManagementRepository userManagementRepository) {
        this.userService = userService;
        this.userManagementRepository = userManagementRepository;
    }

    @Autowired
    private TripRepository tripRepository;


    // get trip by tripcode

//    @GetMapping("/trip")
//    public ResponseEntity<StandardResponseDto<?>> getTrip(
//            @RequestParam String tripCode
//    ) {
//        System.out.println(tripCode);
//        String tripName = userService.getTrip(tripCode).getName();                                      // ? = wildcard and allows us to return whatever we placed inside diamond brackets
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new StandardResponseDto<>(
//                        true,
//                        "Your trip is " + tripName ,
////                        "message",
//                        String.format("The  provided: %s", tripCode)                                      // string interpolation
//                ));
//    }
//
//
//    // get all trips
//
//
//// ? = wildcard and allows us to return whatever we placed inside diamond brackets
//// ? = wildcard and allows us to return whatever we placed inside diamond brackets
////string format =  string interpolation
//
//
//    // get trips
//
//    @GetMapping("/trips")
//    public ResponseEntity<List<Trip>> getTrips() {
//        List<Trip> trips = userService.getAllTrips();
//        return ResponseEntity.status(HttpStatus.OK).body(trips);
//    }
//
//
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
////            @RequestBody NewTripDto newTripDto
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
//        try {
//            Trip newTrip = userService.createTrip(newTripDto);
//            return ResponseEntity
//                    .status(HttpStatus.CREATED)
//                    .body(new StandardResponseDto<>(
//                            true,
//                            "New trip created.",
//                            newTrip
//                    ));
//        }
//        catch (RuntimeException re) {
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(new StandardResponseDto<>(
//                            false,
//                            re.getMessage(),
//                            null
//                    ));
//        }
//    }
//
//
//
//
//    @PatchMapping("/trip/add_friend")
//    public ResponseEntity<StandardResponseDto<?>> addFriendToTrip() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new StandardResponseDto<>(
//                        false,
//                        "Endpoint not yet implemented",
//                        null
//                ));
//    }
//
//
//
//    // delete/ cancel trip
//    @DeleteMapping("/cancelTrip/{tripCode}")
//    public ResponseEntity<String> cancelTrip(String tripCode) {
//
//        // get a trip passing the UUID
////        Optional<Trip> requested_trip = tripRepository.findByTripCode(tripCode);
//
////        List<String> tripCodes = userService.getAllTrips().stream();
//
//        List<String> tripCodes = userService.getAllTrips()
//                .stream()
//                .map(trip -> {return trip.getTripCode();})
//                .filter(trip -> {return trip.equals(tripCode);})
//                .collect(Collectors.toList());
//
////        .map(TripDto::tripCode)                                          // create a list of tripcodes
////                .filter(f -> f.equals(tripCode)).toList();
//        if (!tripCodes.isEmpty()) {
//            ResponseEntity<String> OUT = ResponseEntity.ok("Trip" + userService.getAllTrips()
//                    .stream()
//                    .map(Trip::getName).collect(Collectors.toList())
//                    + " has been cancelled.");
//            tripRepository.cancelTrip(tripCode);
//            return OUT;
//        }
//        return ResponseEntity.badRequest().build();
//    }
//
//

    @GetMapping("/getUserByName/{firstname}")
    public Optional<ApplicationUser> getUserByName(@PathVariable("firstname") String firstname) {
        return userService.getUserByName(firstname);
    }


//@GetMapping("/findUserByName/{name}")
//    public List findUserByName(@PathVariable("name") String name){
//        return userService.findUserByName(name);
//}







}





