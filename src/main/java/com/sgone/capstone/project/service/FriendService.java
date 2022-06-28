package com.sgone.capstone.project.service;


import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Friend;
import com.sgone.capstone.project.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

@Autowired
    private FriendRepository friendRepository;

    public FriendService (FriendRepository friendRepository){
        this.friendRepository = friendRepository;
    }

    public Friend findFriend_a(Long id) {
        return friendRepository.findFriend_a(id);
    }

    public Friend findFriend_b(Long id) {
        return friendRepository.findFriend_b(id);
    }

    public Friend findFriendPair(Long id) {
        return friendRepository.findFriendPair(id);
    }

    public String deleteFriendById(Long id){

        Friend friend = friendRepository.findFriendPair(id);
        friendRepository.delete(friend);

        return "Deleted Friend " + friend.getId() + ". If this was a mistake, you can add a new post using the Add post function!";

    }



}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend
