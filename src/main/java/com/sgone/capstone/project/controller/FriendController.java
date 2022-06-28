package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomApplicationUserDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Friend;
import com.sgone.capstone.project.repository.FriendRepository;
import com.sgone.capstone.project.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private final FriendRepository friendRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private FriendService friendService;

    public FriendController(FriendRepository friendRepository, FriendService friendService, UserController userController) {
        this.friendRepository = friendRepository;
        this.friendService = friendService;
        this.userController = userController;
    }

    @GetMapping("/friend")
    public ResponseEntity<List<Friend>> findAllFriends() {
        List<Friend> friendList = friendRepository.findAll();
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }


    @DeleteMapping("/friend/{id}")
    public String deleteFriendById(@PathVariable(value = "id") Long id) {
        return friendService.deleteFriendById(id);
    }

//works but error 200 is outputted - this produced infinite JSON response
    @PostMapping("/friend/{currentUser}/{friendToAdd}")
    public ResponseEntity <Friend> addFriend(@PathVariable("currentUser") String currentUser, @PathVariable("friendToAdd") String friendToAdd) {
        Friend friend = friendService.addFriend(currentUser, friendToAdd);
        return new ResponseEntity<>(friend,HttpStatus.OK);
    }


}
//get all friends from friends list
//delete friends from friends list
//add a friend with other person's consent

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend