package com.sgone.capstone.project.service;


import com.sgone.capstone.dto.CustomApplicationUserDto;
import com.sgone.capstone.dto.request.NewFriendDto;
import com.sgone.capstone.project.controller.UserController;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Friend;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.repository.FriendRepository;
import com.sgone.capstone.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserController userController;

    public FriendService(FriendRepository friendRepository, UserController userController, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.userController = userController;
        this.userRepository = userRepository;
    }



    public Friend findFriendPair(String friend_a_name, String friend_b_name) {
        return friendRepository.findFriendPair(friend_a_name, friend_b_name);
    }

//    public String deleteFriendById(Long id) {
//
//        try {
//            Friend friend = friendRepository.;
//            friendRepository.delete(friend);
//            return "Deleted Friend " + friend.getId() + ". If this was a mistake, you can add a new post using the Add post function!";
//        }
//        catch(InvalidDataAccessApiUsageException e){
////            System.out.println(e);
//            return "Id entered does not exist!";
//        }
//    }

    public String addFriend(String currentUserFirstName, String currentUserLastName, String friendToAddFirstName, String friendToAddLastName) {

        ApplicationUser userC = userRepository.getUserByName(currentUserFirstName, currentUserLastName);
        ApplicationUser userF = userRepository.getUserByName(friendToAddFirstName, friendToAddLastName);

        // Creating two new Users...
        // userC corresponds to the current user. currentUserFirstName, currentUserLastName should correspond to whoever is logged in
        // userF corresponds to the TARGET user to be added as a friend, by their first and last names


        //creating two new Friends - friendLT and friendLF for the corresponding users
        //the friend objects call getters from the corresponding user class

        Friend friendTA = new Friend(currentUserFirstName, userC, friendToAddFirstName, userF);
        Friend existingFriendCombo1 = friendRepository.findFriendPair(currentUserFirstName, friendToAddFirstName);
        Friend existingFriendCombo2 = friendRepository.findFriendPair(friendToAddFirstName, currentUserFirstName);



        if(userF == null) {
            throw new InvalidDataAccessApiUsageException("User does not exist! Check the details are right and try again, " + userC.getFirstname() + " " + userC.getLastname());
        } else if (userC == userF) {
            throw new InvalidDataAccessApiUsageException("Oi, big boy! You can't be friends with yourself! " + userC.getFirstname() + " " + userC.getLastname());
        } else if (existingFriendCombo1 == null && existingFriendCombo2 == null) {
            friendRepository.save(friendTA);
        } else {
            throw new InvalidDataAccessApiUsageException("You're already friends with " + userF.getFirstname() + "!");
        }

        //friendTA.getFriend_a_name().equals(existingFriend.getFriend_a_name().contains(currentUserFirstName)) && friendTA.getFriend_b_name().equals(existingFriend.getFriend_b_name())

        return "You have added " + userF.getFirstname() + " to your friend list, " + userC.getFirstname() + " " + userC.getLastname();
    }


    public String addFriendByUsername(String currentUserUsername, String friendToAddUsername) {

        ApplicationUser userC = userRepository.getUserByUserName(currentUserUsername);
        ApplicationUser userF = userRepository.getUserByUserName(friendToAddUsername);
        if(userF == null) {
            throw new InvalidDataAccessApiUsageException("Username does not exist! Check the details are right and try again, " + userC.getUsername());
        }

        Friend friendTA = new Friend(userC, userF);
        friendRepository.save(friendTA);

        return "You have added " + userF.getUsername() + " to your friend list, " + userC.getUsername();
    }


}


//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend
