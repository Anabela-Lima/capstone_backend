package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.TripAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TripAssignmentRepository extends JpaRepository<TripAssignment, Long> {

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM trip_assignment WHERE trip_id = ?1",
            nativeQuery = true
    )
    Integer deleteByTripId(Long tripId);

    @Query(value="SELECT * FROM trip_assignment " +
            "WHERE application_user_id = :USER_ID", nativeQuery = true)
    List<Long> returnAllTripsByUser(@Param("USER_ID") Long userID);

    @Query(value="SELECT application_user_id FROM trip_assignment\n" +
            "WHERE trip_id = :TRIP_ID\n" +
            "ORDER BY id\n" +
            "LIMIT 1", nativeQuery = true)
    Long returnOrganiserOfTrip(@Param("TRIP_ID") Long tripID);


}
