package com.sgone.capstone.dataloader.repository;

import com.sgone.capstone.project.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderDayRepository extends JpaRepository<Day, Long> {
}
