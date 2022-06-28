package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.DayActivity;
import com.sgone.capstone.project.model.Enum.DayActivityType;
import com.sgone.capstone.project.repository.DayActivityRepository;
import com.sgone.capstone.project.repository.DayRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DayActivityController {
    private DayActivityRepository dayActivityRepository;
    private DayRepository dayRepository;

    public DayActivityController(DayActivityRepository dayActivityRepository, DayRepository dayRepository) {
        this.dayActivityRepository = dayActivityRepository;
        this.dayRepository = dayRepository;
    }

    @PostMapping("/dayActivity")
    public void createDayActivity(
//            @RequestParam DayActivityType activityType,
                                  @RequestParam String location,
                                  @RequestParam String name,
                                  @RequestParam Double price,
                                  @RequestParam Long day_id) {
        if (dayRepository.findById(day_id).isPresent()) {
            DayActivity dayActivity = new DayActivity(name, location, price, null,
                    dayRepository.getById(day_id));
            dayActivityRepository.save(dayActivity);

        }
    }
}
