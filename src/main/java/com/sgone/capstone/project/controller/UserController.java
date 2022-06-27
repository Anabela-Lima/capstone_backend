package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomApplicationUserDto;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import com.sgone.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;

    public UserController() {}

    // get trip by trip code
    @GetMapping("/trip")
    // ? = wildcard and allows us to return whatever we placed inside diamond brackets
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
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) String name,
            @RequestParam(required = true) String country,
            @RequestParam(required = true) String description,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(required = true) LocalDateTime startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam(required = true) LocalDateTime endDate
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
            Trip newTrip = userService.createTrip(newTripDto);
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
                    return new CustomApplicationUserDto(
                            user.getId(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getEmail(),
                            user.getMobile(),
                            user.getFirstname(),
                            user.getLastname()
                    );
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(customUsers);
    }



    // delete and/or cancel trip
    @DeleteMapping("/cancelTrip/{tripCode}")
    public ResponseEntity<String> cancelTrip(String tripCode) {

        // get a trip passing the UUID
//        Optional<Trip> requested_trip = tripRepository.findByTripCode(tripCode);

//        List<String> tripCodes = userService.getAllTrips().stream();

        List<String> tripCodes = userService.getAllTrips()
                .stream()
                .map(trip -> {return trip.getTripCode();})
                .filter(trip -> {return trip.equals(tripCode);})
                .collect(Collectors.toList());

//        .map(TripDto::tripCode)                                          // create a list of tripcodes
//                .filter(f -> f.equals(tripCode)).toList();
        if (!tripCodes.isEmpty()) {
            ResponseEntity<String> OUT = ResponseEntity.ok("Trip" + userService.getAllTrips()
                    .stream()
                    .map(Trip::getName).collect(Collectors.toList())
                    + " has been cancelled.");
            tripRepository.cancelTrip(tripCode);
            return OUT;
        }
        return ResponseEntity.badRequest().build();
    }













}





