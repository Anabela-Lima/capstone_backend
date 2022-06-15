package com.sgone.capstone.security.management.user;

import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/management/user")
public class UserManagementController {

    private UserManagementService userManagementService;

    public UserManagementController() {}

    @Autowired
    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    @GetMapping("/get_all")
    @PreAuthorize("hasAuthority('app_user:read_all')")
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


    @GetMapping("/get_single")
    @PreAuthorize("hasAuthority('app_user:read_all')")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> getSingleUser(
            @RequestParam(required = true, defaultValue = "0") Long userId
    ) {

        try {
            ApplicationUser singleApplicationUser = userManagementService.getSingleUser(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StandardResponseDto<>(
                            true,
                            String.format("User %s found.", singleApplicationUser.getUsername()),
                            singleApplicationUser
                    ));
        }
        catch (RuntimeException re) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponseDto<>(
                            false,
                            re.getMessage(),
                            null
                    ));
        }

    }


}
