package com.sgone.capstone.project.controller;

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

    @GetMapping("/friend")
    public ResponseEntity<List<Friend>> findAllFriends() {
        return new ResponseEntity<>(friendRepository.findAll(), HttpStatus.OK);
    }


    @DeleteMapping("/friend/{id}")
    public void deleteFriendById(@PathVariable(value = "id") Long id) {
        friendService.deleteFriendById(id);
    }


    @PostMapping("/friend/{friend_b}")
    public ResponseEntity<List<Friend>> addFriend(@RequestBody Friend friend) {
        friendRepository.save(friend);
        return new ResponseEntity<>(friendRepository.findAll(), HttpStatus.CREATED);
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