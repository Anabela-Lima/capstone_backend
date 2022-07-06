package com.sgone.capstone.project.repository;

import com.sgone.capstone.project.model.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TripRepository tripRepository;

    @Test
    public void canDeleteTrip(){
       tripRepository.cancelTrip("f65d4af5-111e-4b61-9b16-ce7e594f315c");
    }

}
