package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.TripAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripAssignmentRepository extends JpaRepository<TripAssignment, Long> {


}
