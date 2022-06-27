package com.sgone.capstone.security.management.user;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserManagementRepository extends JpaRepository<ApplicationUser, Long> {

    @Query(
            value = "SELECT " +
                    "* " +
                    "FROM " +
                    "APPLICATION_USER " +
                    "WHERE " +
                    "is_admin = false " +
                    "AND " +
                    "is_owner = false",
            nativeQuery = true
    )
    List<ApplicationUser> getAll();

    @Query(
            value = "SELECT " +
                    "* " +
                    "FROM " +
                    "APPLICATION_USER " +
                    "WHERE " +
                    "id = ?1 " +
                    "AND " +
                    "is_admin = false " +
                    "AND " +
                    "is_owner = false",
            nativeQuery = true
    )
    Optional<ApplicationUser> getSingle(Long userId);
}
