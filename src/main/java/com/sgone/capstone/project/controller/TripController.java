package com.sgone.capstone.project.controller;

import com.sgone.capstone.project.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips/")
public class TripController {

    @Autowired
    private TripService tripService;

    public TripController() {}


}
