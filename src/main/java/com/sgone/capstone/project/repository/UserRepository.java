package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
