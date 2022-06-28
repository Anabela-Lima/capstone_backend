package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(
            value = "SELECT " +
                    "* " +
                    "FROM " +
                    "application_user " +
                    "WHERE " +
                    "is_Admin = false " +
                    "AND " +
                    "is_owner = false " +
                    "AND " +
                    "id = ?1",
            nativeQuery = true
    )
    Optional<ApplicationUser> getAllUsers(Long userId);


    @Query(
            value = "SELECT " +
                    "* " +
                    "FROM " +
                    "APPLICATION_USER " +
                    "WHERE " +
                    "firstname = ?1 ",
            nativeQuery = true
    )
    ApplicationUser getUserByName(String firstname);

    @Query(value = "SELECT * FROM users WHERE id = ?", nativeQuery = true)
    Optional<ApplicationUser> getUser(Long userId);

    @Query(
            value = "SELECT " +
                    "* " +
                    "FROM " +
                    "users " +
                    "WHERE " +
                    "firstname = ?1 " +
                    "AND " +
                    "lastname = ?2",
            nativeQuery = true
    )
    ApplicationUser findUserByFirstAndLastNames(
            @Param("first_name") String firstname,
            @Param("last_name") String lastname
    );
}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend