package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {

    @Query(
            value = "SELECT * FROM day WHERE trip_id = ?1",
            nativeQuery = true
    )
    List<Day> getAllDaysByTripId(Long tripId);


    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM day WHERE trip_id = ?1",
            nativeQuery = true
    )
    Integer deleteByTripId(Long tripId);



    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM day WHERE id = ?1",
            nativeQuery = true
    )
    Integer deleteDayById(Long dayId);
}