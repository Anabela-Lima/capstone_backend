package com.sgone.capstone.project.service;

import com.sgone.capstone.project.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public TripService() {}


}
