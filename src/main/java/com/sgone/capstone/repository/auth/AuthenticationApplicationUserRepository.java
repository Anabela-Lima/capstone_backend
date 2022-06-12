package com.sgone.capstone.repository.auth;

import com.sgone.capstone.model.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(
            value = "SELECT * FROM APPLICATION_USER WHERE username = ?1",
            nativeQuery = true
    )
    Optional<ApplicationUser> findUserByUsername(String username);



}
