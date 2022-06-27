package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(value = "SELECT * FROM users WHERE firstname = ?1 AND lastname = ?2", nativeQuery = true)
    ApplicationUser findUserByFirstAndLastNames(@Param("first_name") String firstname, @Param("last_name") String lastname);
}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend