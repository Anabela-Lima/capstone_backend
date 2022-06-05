package com.sgone.capstone.controller;


import com.sgone.capstone.dto.StandardResponseDto;
import com.sgone.capstone.model.User;
import com.sgone.capstone.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/api/v1/users")
public class UserManagementController {

    private UserManagementService userManagementService;

    public UserManagementController() {}

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<StandardResponseDto<List<User>>> getAllUsers() {
        List<User> allUsers = userManagementService.getAllUsers();

        if (allUsers.isEmpty()) {
            StandardResponseDto<List<User>> response =
                    new StandardResponseDto<>(
                            false,
                            "No users found!",
                            new ArrayList<>()
                    );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        StandardResponseDto<List<User>> response =
                new StandardResponseDto<>(
                        true,
                        String.format("%s users found.", allUsers.size()),
                        allUsers
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get_single/{userId}")
    public ResponseEntity<StandardResponseDto<User>> getSingleUser(@PathVariable Long userId) {

        try {
            User singleUser = userManagementService.getSingleUser(userId);
            StandardResponseDto<User> response =
                    new StandardResponseDto<>(
                            true,
                            "PLACEHOLDER MESSAGE",
                            singleUser
                    );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (RuntimeException re) {
            StandardResponseDto<User> response =
                    new StandardResponseDto<>(
                            false,
                            re.getMessage(),
                            null
                    );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }
}
