package com.sgone.capstone.security.management.admin;

import com.sgone.capstone.dto.request.AdminDto;
import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import com.sgone.capstone.security.management.admin.AdminManagementRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AdminManagementService {

    private AdminManagementRepository adminManagementRepository;
    private PasswordEncoder passwordEncoder;

    public AdminManagementService() {}

    public AdminManagementService(
            AdminManagementRepository adminManagementRepository,
            PasswordEncoder passwordEncoder) {
        this.adminManagementRepository = adminManagementRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ApplicationUser> getAllAdmins() {
        List<ApplicationUser> allAdmins = adminManagementRepository.getAll();

        if (allAdmins.isEmpty()) {
            throw new RuntimeException("No admins found in database.");
        }

        return allAdmins;
    }

    public ApplicationUser getSingleAdmin(Long userId) {
        Optional<ApplicationUser> adminOptional = adminManagementRepository.getSingle(userId);

        if (!adminOptional.isPresent()) {
            throw new RuntimeException("No admin found.");
        }

        return adminOptional.get();
    }


    public ApplicationUser addNewAdmin(AdminDto adminDto) {
        String mobileString = adminDto.getMobile().trim();
        Long mobile;

        try {
            mobile = Long.parseLong(mobileString);
        }
        catch (NumberFormatException nfe) {
            throw new RuntimeException(
                    "Mobile provided contains invalid characters. Must contain only numbers."
            );
        }

        try {
             return adminManagementRepository
                    .save(
                            new ApplicationUser(
                                    adminDto.getUsername().trim(),
                                    passwordEncoder.encode(adminDto.getPassword().trim()),
                                    adminDto.getEmail().trim(),
                                    mobile,
                                    "firstname",
                                    "lastname",
                                    true,
                                    false
                            )
                    );
        }
        catch (DataIntegrityViolationException dive) {
            String error = dive.getCause().getCause().getMessage();

            String duplicatedField =
                    error.contains("username") ? "username" :
                            error.contains("email") ? "email" :
                                    error.contains("mobile") ? "mobile" : "";

            if (duplicatedField.length() == 0) {
                throw new RuntimeException("An error has occurred, please try again.");
            }

            throw new RuntimeException(
                    String.format("This %s is already used, sign in instead?", duplicatedField));
        }
    }
}
