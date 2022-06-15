package com.sgone.capstone._dataloader;

import com.sgone.capstone.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderRepository extends JpaRepository<ApplicationUser, Long> {

}
