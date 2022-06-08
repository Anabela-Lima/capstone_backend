package com.sgone.capstone.service;

import com.sgone.capstone.model.ApplicationUser;
import com.sgone.capstone.repository.management.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    private UserManagementRepository userManagementRepository;

    public UserManagementService() {}

    @Autowired
    public UserManagementService(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    public List<ApplicationUser> getAllUsers() {
        List<ApplicationUser> allApplicationUsers = userManagementRepository.getAll();

        if (allApplicationUsers.isEmpty()) {
            throw new RuntimeException("No users found in database!");
        }

        return allApplicationUsers;
    }

    public ApplicationUser getSingleUser(Long userId) {
        Optional<ApplicationUser> userOptional = userManagementRepository.getSingle(userId);

        if (!userOptional.isPresent()) {
            throw new RuntimeException("No user found!");
        }

        return userOptional.get();
    }

}
