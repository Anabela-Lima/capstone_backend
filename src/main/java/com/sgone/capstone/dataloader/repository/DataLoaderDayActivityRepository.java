package com.sgone.capstone.dataloader.repository;

import com.sgone.capstone.project.model.DayActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderDayActivityRepository extends JpaRepository<DayActivity, Long> {
}
