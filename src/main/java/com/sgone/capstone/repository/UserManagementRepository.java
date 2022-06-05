package com.sgone.capstone.repository;

import com.sgone.capstone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserManagementRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM USER",
            nativeQuery = true
    )
    List<User> getAll();

    @Query(
            value = "SELECT * FROM USER WHERE id = ?1",
            nativeQuery = true
    )
    Optional<User> getSingle(Long userId);
}
