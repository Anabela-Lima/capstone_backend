package com.sgone.capstone.dataloader.repository;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

}
