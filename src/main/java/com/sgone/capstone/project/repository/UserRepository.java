package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {


}
