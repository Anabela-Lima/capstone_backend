package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.DayActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DayActivityRepository extends JpaRepository<DayActivity, Long> {

    @Query(
            value = "SELECT * FROM day_activity WHERE day_id = ?1",
            nativeQuery = true
    )
    List<DayActivity> getAllDayActivityByDayId(Long dayId);


    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM day_activity WHERE day_id = ?1",
            nativeQuery = true
    )
    Integer removeByDayId(Long dayId);

    @Query(value="SELECT application_user_id FROM day_activity \n" +
            "INNER JOIN day\n" +
            "ON day_activity.day_id = day.id\n" +
            "INNER JOIN trip_assignment \n" +
            "ON day.trip_id = trip_assignment.trip_id\n" +
            "WHERE day_activity.id = :DAY_ACTIVITY_ID",
            nativeQuery = true)
    List<Long> findUsersByDayActivityID(@Param("DAY_ACTIVITY_ID") Long dayActivityID);

    @Modifying
    @Query(value = "DELETE FROM day_activity WHERE id = :DAY_ACTIVITY_ID", nativeQuery = true)
    @Transactional
    void deleteDayActivity(@Param("DAY_ACTIVITY_ID") Long dayActivityID);
}
