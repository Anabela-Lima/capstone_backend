package com.sgone.capstone.service;

import com.sgone.capstone.model.User;
import com.sgone.capstone.repository.UserManagementRepository;
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

    public List<User> getAllUsers() {
        return userManagementRepository.getAll();
    }

    public User getSingleUser(Long userId) {
        Optional<User> userOptional = userManagementRepository.getSingle(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("No user found!");
        }

        return userOptional.get();
    }

}
