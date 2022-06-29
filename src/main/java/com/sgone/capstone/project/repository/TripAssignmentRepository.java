package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.TripAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TripAssignmentRepository extends JpaRepository<TripAssignment, Long> {

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM trip_assignment WHERE trip_id = ?1",
            nativeQuery = true
    )
    Integer deleteByTripId(Long tripId);



}
