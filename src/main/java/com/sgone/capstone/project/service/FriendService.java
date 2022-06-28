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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public FriendService (FriendRepository friendRepository, UserController userController, UserRepository userRepository){
        this.friendRepository = friendRepository;
        this.userController = userController;
        this.userRepository = userRepository;
    }



    public Friend findFriendPair(Long id) {
        return friendRepository.findFriendPair(id);
    }

    public String deleteFriendById(Long id){

        Friend friend = friendRepository.findFriendPair(id);
        friendRepository.delete(friend);

//        try {
//        } catch (NullPointerException e){
//            if(friend == null) {
//                e.printStackTrace();
//                System.out.println("No matching friend pairing could be found for id: " + id);
//            }

        return "Deleted Friend " + friend.getId() + ". If this was a mistake, you can add a new post using the Add post function!";

    }

    public Friend addFriend(String currentUser, String friendToAdd){

            ApplicationUser userF = userRepository.getUserByName(friendToAdd);
            ApplicationUser userC = userRepository.getUserByName(currentUser);
            Friend friendTA = new Friend(userC, userF);

            //creating two new Users - userT (the target user, who's list we want to update) and the userF (friend to be added)
            //creating two new Friends - friendLT and friendLF for the corresponding users
            //the friend objects call getters from the corresponding user class

        return friendRepository.save(friendTA);

//        List<ApplicationUser> users = userRepository.findAll();
//        List<CustomApplicationUserDto> usersNamedKeyword = users
//                .stream()
//                .map(user -> {
//                    Set<TripAssignment> tripAssignments = user.getTripAssignments();
//                    Set<Long> tripAssignmentId = tripAssignments
//                            .stream()
//                            .map(tripAssignment -> {
//                                return tripAssignment.getId();
//                            })
//                            .collect(Collectors.toSet());
//                    return new CustomApplicationUserDto(
//                            user.getId(),
//                            user.getUsername(),
//                            user.getPassword(),
//                            user.getEmail(),
//                            user.getMobile(),
//                            user.getFirstname(),
//                            user.getLastname(),
//                            tripAssignmentId
//                    );
//                })
//                .filter(s -> s.getFirstname().contains(firstname)).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.OK).body(usersNamedKeyword);


        }
    }




//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend
