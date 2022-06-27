package com.sgone.capstone.dataloader.repository;

import com.sgone.capstone.project.model.DayActivityAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoaderDayActivityAssignmentRepository extends JpaRepository<DayActivityAssignment, Long> {
}
