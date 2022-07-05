package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.TripPieChart;
import com.sgone.capstone.project.repository.DayActivityRepository;
import com.sgone.capstone.project.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trips/")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private DayActivityRepository dayActivityRepository;

    public TripController() {}

    @GetMapping("/pieChartPercentages")
    ResponseEntity<TripPieChart> getPieChartByTrip(@RequestParam Long tripID) {

        Double tripBudget;

        if (dayActivityRepository.addUpAllDayBudgetsByTrip(tripID) == null) {
            if (dayActivityRepository.addUpPriceByTrip(tripID) != null) {
                tripBudget = dayActivityRepository.addUpPriceByTrip(tripID);
            } else {
                return ResponseEntity.ok().body(new TripPieChart(tripID,
                        0.0, 0.0, 0.0, 0.0,
                        0.0, 0.0));
            }
        }


        tripBudget = dayActivityRepository.addUpAllDayBudgetsByTrip(tripID);

        TripPieChart tripPieChart;

        if (tripBudget == 0) {
            tripBudget = dayActivityRepository.addUpPriceByTrip(tripID);

        }

        if (tripBudget == 0) {
            tripBudget = 1.0;
        }

        tripPieChart = new TripPieChart(tripID,
                dayActivityRepository.addUpPriceByTrip(tripID)/tripBudget,
                dayActivityRepository.addUpCategoryPriceByTrip(tripID, 0)/tripBudget,
                dayActivityRepository.addUpCategoryPriceByTrip(tripID, 1)/tripBudget,
                dayActivityRepository.addUpCategoryPriceByTrip(tripID, 2)/tripBudget,
                dayActivityRepository.addUpCategoryPriceByTrip(tripID, 3)/tripBudget,
                dayActivityRepository.addUpCategoryPriceByTrip(tripID, 4)/tripBudget
                );

        return ResponseEntity.ok().body(tripPieChart);
    }

}
