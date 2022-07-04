package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Friend;
import com.sgone.capstone.project.repository.FriendRepository;
import com.sgone.capstone.project.repository.UserRepository;
import com.sgone.capstone.project.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private final FriendRepository friendRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private FriendService friendService;

    public FriendController(FriendRepository friendRepository, UserRepository userRepository, FriendService friendService, UserController userController) {
        this.friendRepository = friendRepository;
        this.friendService = friendService;
        this.userController = userController;
        this.userRepository = userRepository;
    }

    @GetMapping("/friend")
    public ResponseEntity<List<Friend>> findAllFriends() {
        List<Friend> friendList = friendRepository.findAll();
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    @GetMapping("/friendsByID")
    public ResponseEntity<List<ApplicationUser>> allFriendsById(@RequestParam Long userID) {
        List<Long> allFriends = friendRepository.findFriendsByID(userID);
        List<ApplicationUser> friends = new ArrayList<>();
        for (Long friendID : allFriends) {
            friends.add(userRepository.findById(friendID)
                    .orElseThrow());
        }
        return ResponseEntity.ok().body(friends);
    }

    @GetMapping("/findFriendPairUsername/{username_a}/{username_b}")
    public Friend findFriendPairUsername(String username_a, String username_b) {
        return friendService.findFriendPairUsername(username_a, username_b);
    }


//    @DeleteMapping("/friend/{id}")
//    public String deleteFriendById(@PathVariable(value = "id") Long id) {
//        return friendService.deleteFriendById(id);
//    }


    @PostMapping("/addFriend/{currentUserUsername}/{friendToAddUsername}")
    public String addFriend(@PathVariable("currentUserUsername") String currentUserUsername,
                            @PathVariable("friendToAddUsername") String friendToAddUsername

                            ) throws Exception{
        return friendService.addFriend(currentUserUsername, friendToAddUsername);
    }



//    @PostMapping("/addFriendByUsername/{currentUserUsername}/{friendToAddUsername}")
//    public String addFriendByUsername(@PathVariable("currentUserUsername") @RequestParam(required = false) String currentUserUsername,
//                                      @PathVariable("friendToAddUsername") @RequestParam(required = false) String friendToAddUsername
//
//    ) throws Exception{
//        return friendService.addFriendByUsername(currentUserUsername, friendToAddUsername);
//    }

}
//get all friends from friends list
//delete friends from friends list
//add a friend with other person's consent

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend