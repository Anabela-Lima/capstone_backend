package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.DayActivityAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DayActivityAssignmentRepository extends JpaRepository<DayActivityAssignment, Long> {

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM day_activity_assignment WHERE day_activity_id = ?1",
            nativeQuery = true
    )
    Integer deleteByDayActivityId(Long dayActivityId);

    @Modifying
    @Query(value = "DELETE FROM day_activity_assignment WHERE application_user_id = :USER_ID " +
            "AND day_activity_id = :DAY_ACTIVITY_ID", nativeQuery = true)
    @Transactional
    void deleteUserFromActivity(@Param("USER_ID") Long userID, @Param("DAY_ACTIVITY_ID") Long dayActivityID);

    @Modifying
    @Query(value="UPDATE day_activity_assignment\n" +
            "SET should_pay = (ROUND(CAST(\n" +
            "(SELECT price FROM day_activity WHERE id = 1)/(SELECT COUNT(*) FROM day_activity_assignment" +
            " WHERE day_activity_assignment.day_activity_id = 1)\n" +
            "AS numeric), 2))\n" +
            "WHERE day_activity_id = 1",
            nativeQuery = true)
    @Transactional
    void SplitCostEvenly(@Param("DAY_ACTIVITY_ID") Long dayActivityID);


}
