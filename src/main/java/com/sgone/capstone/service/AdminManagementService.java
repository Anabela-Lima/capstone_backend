package com.sgone.capstone.service;

import com.sgone.capstone.model.ApplicationUser;
import com.sgone.capstone.repository.management.AdminManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminManagementService {

    private AdminManagementRepository adminManagementRepository;

    public AdminManagementService() {}

    @Autowired
    public AdminManagementService(AdminManagementRepository adminManagementRepository) {
        this.adminManagementRepository = adminManagementRepository;
    }

    public List<ApplicationUser> getAllAdmins() {
        List<ApplicationUser> allAdmins = adminManagementRepository.getAll();

        if (allAdmins.isEmpty()) {
            throw new RuntimeException("No admins found in database.");
        }

        return allAdmins;
    }

    public ApplicationUser getSingle(Long userId) {
        Optional<ApplicationUser> adminOptional = adminManagementRepository.getSingle(userId);

        if (!adminOptional.isPresent()) {
            throw new RuntimeException("No admin found.");
        }

        return adminOptional.get();
    }
}
