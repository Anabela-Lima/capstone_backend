package com.sgone.capstone.security.management;

import com.sgone.capstone.dto.request.AdminDto;
import com.sgone.capstone.dto.response.StandardResponseDto;
import com.sgone.capstone.model.ApplicationUser;
import com.sgone.capstone.security.management.AdminManagementService;
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
