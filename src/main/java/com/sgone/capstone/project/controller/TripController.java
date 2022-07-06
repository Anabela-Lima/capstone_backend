package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.model.MoneyOwed;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.TripPieChart;
import com.sgone.capstone.project.repository.DayActivityRepository;
import com.sgone.capstone.project.repository.MoneyOwedRepository;
import com.sgone.capstone.project.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trips/")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private DayActivityRepository dayActivityRepository;

    @Autowired
    private MoneyOwedRepository moneyOwedRepository;

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

    @GetMapping("/getReportByTrip")
    ResponseEntity<List<MoneyOwed>> getReportByTrip(@RequestParam Long tripID) {
        List<MoneyOwed> allMoneyOwed = moneyOwedRepository.findAll();
        List<MoneyOwed> moneyOwedByTrip = new ArrayList<>();

        for (MoneyOwed moneyOwed : allMoneyOwed) {
            if (moneyOwed.getTrip().getId() == tripID) {
                moneyOwedByTrip.add(moneyOwed);
            }
        }

        return ResponseEntity.ok().body(moneyOwedByTrip);
    }

    @GetMapping("/hasReportBeenGeneratedByTrip")
    public Map<String, Boolean> hasReportBeenGenerated(@RequestParam Long tripID) {
        List<MoneyOwed> allMoneyOwed = moneyOwedRepository.findAll();

        for (MoneyOwed moneyOwed : allMoneyOwed) {
            if (moneyOwed.getTrip().getId() == tripID) {
                return Collections.singletonMap("success", true);
            }
        }

        return Collections.singletonMap("success", false);
    }

}
