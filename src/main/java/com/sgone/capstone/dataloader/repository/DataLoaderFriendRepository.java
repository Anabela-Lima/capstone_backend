package com.sgone.capstone.dataloader.repository;

import com.sgone.capstone.project.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderFriendRepository extends JpaRepository<Friend, Long> {
}
