package com.sgone.capstone.controller.management;

import com.sgone.capstone.dto.request.AddNewAdminDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.model.ApplicationUser;
import com.sgone.capstone.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("management/admins")
public class AdminManagementController {

    private AdminManagementService adminManagementService;

    public AdminManagementController() {}

    @Autowired
    public AdminManagementController(AdminManagementService adminManagementService) {
        this.adminManagementService = adminManagementService;
    }

    // TODO: Add @PreAuthorize Annotation to only allow Owner roles
    @GetMapping("/get_all")
    public ResponseEntity<StandardResponseDto<List<ApplicationUser>>> getAllAdmins() {

        try {
            List<ApplicationUser> allAdmins = adminManagementService.getAllAdmins();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StandardResponseDto<>(
                            true,
                            String.format("%s admins found.", allAdmins.size()),
                            allAdmins
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

    // TODO: Add @PreAuthorize Annotation to only allow Owner roles
    @GetMapping("/get_single")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> getSingleAdmin(
            @RequestParam(required = true, defaultValue = "0") Long userId
    ) {

        try {
            ApplicationUser singleAdmin = adminManagementService.getSingleAdmin(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new StandardResponseDto<>(
                            true,
                            String.format("Admin %s found.", singleAdmin.getUsername()),
                            singleAdmin
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

    // TODO: Add @PreAuthorize Annotation to only allow Owner roles
    @PostMapping("/add_new_admin")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> addNewAdmin(
            @RequestBody AddNewAdminDto adminDto
    ) {

        try {
            ApplicationUser newlyAddedAdmin = adminManagementService.addNewAdmin(adminDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new StandardResponseDto<>(
                            true,
                            String.format("New admin %s created", newlyAddedAdmin.getUsername()),
                            newlyAddedAdmin
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
}
