package com.sgone.capstone.project.service;

import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.thymeleaf.util.StringUtils.concat;

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

    public String findUserByFirstAndLastNames(String inputFirstName, String inputLastName){

        String targetUserFirstName = userRepository.findAll().stream()
                .map(user -> user.getFirstname().toLowerCase())
                        .filter(s -> s.contains(inputFirstName.toLowerCase())).toString();

        String targetUserLastName = userRepository.findAll().stream()
                .map(user -> user.getLastname().toLowerCase())
                .filter(s -> s.contains(inputLastName.toLowerCase())).toString();


//        if (targetUserFirstName.isEmpty()) {
//            ArrayList<String> noMatches = new ArrayList<>();
//            noMatches.add("No users found :(");
//            return noMatches;
//        }


        return concat(targetUserFirstName + targetUserLastName);



    }

}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend