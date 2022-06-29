package com.sgone.capstone.project.service;


import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DayService {

    @Autowired
    DayRepository dayRepository;

    public Optional<Day> getDayById(Long id) {

        return
        dayRepository.findById(id);


    }
}
