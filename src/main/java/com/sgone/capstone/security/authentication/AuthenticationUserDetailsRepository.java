package com.sgone.capstone.security.authentication;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  Custom query to find a user by using the username.
 *  <br>
 *  <br>
 *  The interface extends <i>JpaRepository</i>, using a custom query to get a user.
 *  This interface is used only by the <i>AuthenticationApplicationUserService</i>.
 *  <br><br>
 *  <b>This repository is only called during the authentication process.</b>
 */
@Repository
public interface AuthenticationUserDetailsRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(
            value = "SELECT * FROM APPLICATION_USER WHERE username = ?1",
            nativeQuery = true
    )
    Optional<ApplicationUser> findUserByUsername(String username);

}
