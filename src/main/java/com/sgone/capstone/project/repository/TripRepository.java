package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {


    // custom query 1

    // ?1 means the first input parameter
    // writing query method - "repository method"
    @Query (
            value = "SELECT " +
                    "* " +
                    "FROM " +
                    "trip " +
                    "WHERE " +
                    "trip_code = :tripCode",
            nativeQuery = true
    )
    Optional<Trip> findByTripCode(@Param("tripCode") String tripCode);



    // custom query 2
    // query to cancel trip given a uuid
    // INSERT, DELETE queries will return an integer indicating how many rows were modified
    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM trip WHERE trip_code = ?1" ,
            nativeQuery = true
    )
    Integer cancelTrip(String tripCode);





}
