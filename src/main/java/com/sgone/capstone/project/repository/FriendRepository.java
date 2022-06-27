package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Override
    List<Friend> findAll();
}
