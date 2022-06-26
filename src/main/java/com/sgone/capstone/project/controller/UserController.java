package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController() {}

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/trip/new")
    public ResponseEntity<StandardResponseDto<?>> createTrip() {
        return null;
    }


    @PatchMapping("/trip/add_friend")
    public ResponseEntity<StandardResponseDto<?>> addFriendToTrip() {
        return null;
    }
}
