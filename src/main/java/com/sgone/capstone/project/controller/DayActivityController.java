package com.sgone.capstone.project.controller;

import com.sgone.capstone.dto.CustomDayActivityDto;
import com.sgone.capstone.project.model.DayActivity;
import com.sgone.capstone.project.model.DayActivityAssignment;
import com.sgone.capstone.project.model.Enum.DayActivityType;
import com.sgone.capstone.project.repository.DayActivityAssignmentRepository;
import com.sgone.capstone.project.repository.DayActivityRepository;
import com.sgone.capstone.project.repository.DayRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class DayActivityController {
    private DayActivityRepository dayActivityRepository;
    private DayRepository dayRepository;
    private DayActivityAssignmentRepository dayActivityAssignmentRepository;

    public DayActivityController(DayActivityRepository dayActivityRepository, DayRepository dayRepository, DayActivityAssignmentRepository dayActivityAssignmentRepository) {
        this.dayActivityRepository = dayActivityRepository;
        this.dayRepository = dayRepository;
        this.dayActivityAssignmentRepository = dayActivityAssignmentRepository;
    }

    @GetMapping("/dayActivities")
    public ResponseEntity<List<DayActivity>> getActivities() {
        List <DayActivity> dayActivityList = dayActivityRepository.findAll();
//        List<CustomDayActivityDto> dayActivityDtoList = dayActivityList
//                .stream()
//                .map(dayActivity -> {
//                        return new CustomDayActivityDto(
//                                dayActivity.getId(),
//                                dayActivity.getName(),
//                                dayActivity.getLocation(),
//                                dayActivity.getPrice(),
//                                dayActivity.getDayActivityType(),
//                                dayActivity.getDay()
//                        );
//                })
//                .collect(Collectors.toList());
//                return ResponseEntity.status(HttpStatus.OK).body(dayActivityDtoList);
        return ResponseEntity.ok().body(dayActivityList);
    }

    @GetMapping("/DayActivityAssignmentsByDayActivityID")
    public ResponseEntity<List<DayActivityAssignment>> getActivityAssignments(@RequestParam Long dayActivityID) {
        List<DayActivityAssignment> dayActivityAssignmentList =
        dayActivityAssignmentRepository.returnActivityAssignmentsByActivityID(dayActivityID);
        return ResponseEntity.ok().body(dayActivityAssignmentList);
    }

    @PostMapping("/dayActivity")
    public void createDayActivity(
            @RequestParam DayActivityType activityType,
                                  @RequestParam String location,
                                  @RequestParam String name,
                                  @RequestParam Double price,
                                  @RequestParam Long day_id) {
        if (dayRepository.findById(day_id).isPresent()) {
            DayActivity dayActivity = new DayActivity(name, location, price, activityType,
                    dayRepository.getById(day_id));
            dayActivityRepository.save(dayActivity);

            Long activityID = dayActivity.getId();

            List<Long> usersOnTrip = dayActivityRepository.findUsersByDayActivityID(
                    activityID
            );

            for (Long user_id : usersOnTrip) {
                dayActivityAssignmentRepository.insertEmptyRows(user_id, activityID);
            }

            dayActivityAssignmentRepository.SplitCostEvenly(activityID);

        }
    }

    @DeleteMapping("/deleteDayActivity")
    public void deleteDayActivity(@RequestParam Long dayActivityID) {
        dayActivityAssignmentRepository.deleteByDayActivityId(dayActivityID);
        dayActivityRepository.deleteDayActivity(dayActivityID);
    }
}
