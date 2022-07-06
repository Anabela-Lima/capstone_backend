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
    public void canDeleteTrip(String tripCode){
        Optional<Trip> exists = tripRepository.findByTripCode(tripCode);
        if (!tripCode.equals(exists)) {
            throw new IllegalStateException(
                    "Trip of code " + tripCode + " does not exist"
            );
        }
    tripRepository.cancelTrip(tripCode);
    }

}
