package com.sgone.capstone.project.service;

import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.controller.FriendController;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Friend;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.repository.DayRepository;
import com.sgone.capstone.project.repository.FriendRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FriendServiceTest {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    FriendService friendService;


    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendController friendController;



        @Test
        void contextLoads(){
        }

        @Test
        public void canAddUserName(){

            ApplicationUser applicationUser1 = new ApplicationUser("user1","test","test2@email.com",0152L, "firstname","lastname" ,true ,true);
            ApplicationUser applicationUser2 = new ApplicationUser("user2","testing","test3@email.com",0156L, "firstname1","lastname1" ,true ,true);

            Friend friend = new Friend(1L,applicationUser1,applicationUser2,applicationUser1.getUsername(),applicationUser2.getUsername());
String userA = applicationUser1.getUsername();
String userB = applicationUser2.getUsername();

            Friend exists = friendRepository.findFriendPairUsername(applicationUser1.getUsername(),applicationUser2.getUsername());
            assertEquals(friendRepository.findFriendPairUsername(userA, userB),exists);

        }

    @Test
    public void canGetFriendPairByUserName(){
      assertEquals(null, friendRepository.findFriendPairUsername("scott","naeem"));
        }

}
