package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.DayActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {

    @Query(
            value = "SELECT * FROM day WHERE trip_id = ?1 " +
                    "ORDER BY id",
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


    @Transactional
    @Modifying
    @Query (

            value = "UPDATE day SET(name, budget, date) = (?2, ?3, ?4 ) WHERE id= ?1",
            nativeQuery= true
    )
    Integer updateDay(Long dayID,String name, Double budget, LocalDateTime date);


    @Query(value = "SELECT SUM(price) FROM day\n" +
            "INNER JOIN day_activity \n" +
            "ON day.id = day_activity.day_id\n" +
            "WHERE day.id = :DAY_ID", nativeQuery = true)
    Long daySpend(@Param("DAY_ID") Long dayID);


    @Query(value = "SELECT trip.id FROM trip_assignment INNER JOIN trip ON trip.id = trip_assignment.trip_id WHERE application_user_id = 1 AND trip_code = 883dfea7-7975-4132-b0fc-9aa22b258e96", nativeQuery = true)
    Long testQuery();

    @Modifying
    @Query(value="UPDATE day SET budget = :BUDGET WHERE id = :DAY_ID", nativeQuery = true)
    @Transactional
    void changeBudget(@Param("DAY_ID") Long dayId, @Param("BUDGET") Double budget);

}