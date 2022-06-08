package com.sgone.capstone.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    // Repository dependency goes here

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Load goes here
    }
}