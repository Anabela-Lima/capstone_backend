package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query(value = "SELECT * FROM friend_a WHERE id = ?", nativeQuery = true)
    Friend findFriend_a(Long id);

    @Query(value = "SELECT * FROM friend_b WHERE id = ?", nativeQuery = true)
    Friend findFriend_b(Long id);

    @Query(value = "SELECT * FROM friend WHERE id = ?", nativeQuery = true)
    Friend findFriendPair(Long id);

    @Query(value = "DELETE * FROM friend WHERE id = ?", nativeQuery = true)
    String deleteFriendById(Long id);



}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend