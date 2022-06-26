package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<StandardResponseDto<?>> createTrip() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto<>(
                        false,
                        "Endpoint not yet implemented",
                        null
                ));
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
