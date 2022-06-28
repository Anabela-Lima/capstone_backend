package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.DayActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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




}
