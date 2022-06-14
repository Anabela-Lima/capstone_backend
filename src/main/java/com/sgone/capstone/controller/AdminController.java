package com.sgone.capstone.controller;

import com.sgone.capstone.dto.request.AdminDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.model.entity.ApplicationUser;
import com.sgone.capstone.service.management.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminManagementService adminManagementService;

    public AdminController() {}

    @Autowired
    public AdminController(AdminManagementService adminManagementService) {
        this.adminManagementService = adminManagementService;
    }


    /*
       TODO: Add @PreAuthorize Annotation to only allow Owner roles and permissions

       TODO:
           Permissions allows:
           1. APP_ADMIN_READ_ALL
           2. APP_ADMIN_READ_SELF

    */
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


    /*
       TODO:
            1. Add implementation
            2. Add @PreAuthorize Annotation to only allow Owner roles and permissions

       TODO:
           Permissions allows:
           1. APP_ADMIN_WRITE_SELF

    */
    @PatchMapping("/edit_self")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> adminEditSelf(
            @RequestBody AdminDto adminDto
    ) {
        return null;
    }
}
