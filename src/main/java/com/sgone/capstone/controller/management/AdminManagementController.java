package com.sgone.capstone.controller.management;

import com.sgone.capstone.dto.request.AdminDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.model.entity.ApplicationUser;
import com.sgone.capstone.service.management.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("management/admin")
public class AdminManagementController {

    private AdminManagementService adminManagementService;

    public AdminManagementController() {}

    @Autowired
    public AdminManagementController(AdminManagementService adminManagementService) {
        this.adminManagementService = adminManagementService;
    }


    /*
        TODO: available args for @PreAuthorize annotation
            -
                hasRole('ROLE_')
                hasAnyRole('ROLE_')
                hasAuthority('permission')
                hasAnyAuthority('permission')
            -
     */

    /*
        TODO:
            Permissions allowed:
            1. APP_ADMIN_READ_ALL

     */
    @GetMapping("/get_all")
    @PreAuthorize("hasAuthority('app_admin:read_all')")
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


    /*
        TODO:
            Permissions allowed:
            1. APP_ADMIN_WRITE_ALL

     */
    @PostMapping("/add_new")
    @PreAuthorize("hasAuthority('app_admin:write_all')")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> addNewAdmin(
            @RequestBody AdminDto adminDto
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


    /*
        TODO:
            1. Add implementation

        TODO:
            Permissions allowed:
            1. APP_ADMIN_WRITE_ALL

     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('app_admin:write_all')")
    public ResponseEntity<StandardResponseDto<ApplicationUser>> deleteAdmin(
            @RequestParam(required = true) Long userId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new StandardResponseDto<>(
                        false,
                        "Endpoint not yet implemented",
                        null
                ));
    }
}
