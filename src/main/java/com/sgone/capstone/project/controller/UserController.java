package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController() {}

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/trip/{id}")
    public ResponseEntity<StandardResponseDto<?>> getTrip(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto<>(
                        false,
                        "Endpoint not yet implemented",
                        String.format("The ID provided: %s", id)
                ));
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
//            @RequestBody NewTripDto newTripDto
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
}
