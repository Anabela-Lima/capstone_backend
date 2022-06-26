package com.sgone.capstone.project.service;

import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService() {}

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Trip getTrip() {
        return null;
    }


    public Trip createTrip() {
        return null;
    }


    public Trip addFriendToTrip() {
        return null;
    }

}
