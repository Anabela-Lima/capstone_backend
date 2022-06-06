package com.sgone.capstone.controller;


import com.sgone.capstone.dto.StandardResponseDto;
import com.sgone.capstone.model.ApplicationUser;
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
@RequestMapping("/admin/api/users")
public class UserManagementController {

    private UserManagementService userManagementService;

    public UserManagementController() {}

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<StandardResponseDto<List<ApplicationUser>>> getAllUsers() {

        try {
            List<ApplicationUser> allApplicationUsers = userManagementService.getAllUsers();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StandardResponseDto<>(
                            true,
                            String.format("%s users found.", allApplicationUsers.size()),
                            allApplicationUsers
                    ));
        }
        catch (RuntimeException re) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponseDto<>(
                            false,
                            re.getMessage(),
                            new ArrayList<>()
                    ));
        }
    }

    @GetMapping("/get_single/{userId}")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> getSingleUser(@PathVariable Long userId) {

        try {
            ApplicationUser singleApplicationUser = userManagementService.getSingleUser(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StandardResponseDto<>(
                            true,
                            "PLACEHOLDER MESSAGE",
                            singleApplicationUser
                    ));
        }
        catch (RuntimeException re) {
            StandardResponseDto<ApplicationUser> response =
                    new StandardResponseDto<>(
                            false,
                            re.getMessage(),
                            null
                    );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }
}
