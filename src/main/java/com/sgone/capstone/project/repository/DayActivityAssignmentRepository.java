package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.DayActivityAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
