package com.sgone.capstone.dataloader;

import com.sgone.capstone.dataloader.repository.*;
import com.sgone.capstone.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


@Component
public class DataLoader implements ApplicationRunner {

    // Repository dependency goes here
    private DataLoaderApplicationUserRepository dataLoaderApplicationUserRepository;
    private DataLoaderTripRepository dataLoaderTripRepository;
    private DataLoaderTripAssignmentRepository dataLoaderTripAssignmentRepository;
    private DataLoaderDayRepository dataLoaderDayRepository;

    private DataLoaderFriendRepository dataLoaderFriendRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(){}

    @Autowired
    public DataLoader(DataLoaderApplicationUserRepository dataLoaderApplicationUserRepository, DataLoaderTripRepository dataLoaderTripRepository, DataLoaderTripAssignmentRepository dataLoaderTripAssignmentRepository, DataLoaderDayRepository dataLoaderDayRepository, DataLoaderFriendRepository dataLoaderFriendRepository, PasswordEncoder passwordEncoder) {
        this.dataLoaderApplicationUserRepository = dataLoaderApplicationUserRepository;
        this.dataLoaderTripRepository = dataLoaderTripRepository;
        this.dataLoaderTripAssignmentRepository = dataLoaderTripAssignmentRepository;
        this.dataLoaderDayRepository = dataLoaderDayRepository;
        this.dataLoaderFriendRepository = dataLoaderFriendRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        DataLoaderUsersArray dataLoaderUsersArray = new DataLoaderUsersArray();
        ArrayList<ApplicationUser> users = dataLoaderUsersArray.dataLoaderUsers();

        for (ApplicationUser user: users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            dataLoaderApplicationUserRepository.save(user);
        }

        ApplicationUser ana = new ApplicationUser(
                "ana",
                passwordEncoder.encode("password"),
                "ana@email.com",
                1l,
                "Ana",
                "Lima",
                false,
                false
        );

        ApplicationUser jenna = new ApplicationUser(
                "jenna",
                passwordEncoder.encode("password"),
                "jenna@email.com",
                2l,
                "Jenna",
                "Vlahos",
                false,
                false
        );

        ApplicationUser naeem = new ApplicationUser(
                "naeem",
                passwordEncoder.encode("password"),
                "naeem@email.com",
                3l,
                "Naeem",
                "Khan",
                false,
                false
        );

        ApplicationUser scott = new ApplicationUser(
                "scott",
                passwordEncoder.encode("password"),
                "scott@email.com",
                4l,
                "Scott",
                "Christie",
                false,
                false
        );

        dataLoaderApplicationUserRepository.save(ana);
        dataLoaderApplicationUserRepository.save(jenna);
        dataLoaderApplicationUserRepository.save(naeem);
        dataLoaderApplicationUserRepository.save(scott);

        Trip trip1 = new Trip(
                "trip1",
                5,
                "test trip 1"
        );

        Trip trip2 = new Trip(
                "trip2",
                1,
                "test trip 2"
        );

        Trip trip3 = new Trip(
                "trip3",
                2,
                "test trip 3"
        );

        Trip trip4 = new Trip(
                "trip4",
                4,
                "test trip 4"
        );

        dataLoaderTripRepository.save(trip1);
        dataLoaderTripRepository.save(trip2);
        dataLoaderTripRepository.save(trip3);
        dataLoaderTripRepository.save(trip4);

        TripAssignment tripAssignment1 = new TripAssignment(trip1, ana);
        TripAssignment tripAssignment2 = new TripAssignment(trip1, jenna);
        TripAssignment tripAssignment3 = new TripAssignment(trip3, scott);
        TripAssignment tripAssignment4 = new TripAssignment(trip4, naeem);

        dataLoaderTripAssignmentRepository.save(tripAssignment1);
        dataLoaderTripAssignmentRepository.save(tripAssignment2);
        dataLoaderTripAssignmentRepository.save(tripAssignment3);
        dataLoaderTripAssignmentRepository.save(tripAssignment4);

        Day trip1Day1 = new Day("trip_1_day_1", 200.00, java.sql.Date.valueOf(LocalDate.now()), trip1);
        Day trip1Day2 = new Day("trip_1_day_2", 200.00, java.sql.Date.valueOf(LocalDate.now().plusDays(1)), trip1);

        dataLoaderDayRepository.save(trip1Day1);
        dataLoaderDayRepository.save(trip1Day2);

//        DayActivity dayActivity1 = new DayActivity("")

        Friend friend1 = new Friend(jenna, ana);
        Friend friend2 = new Friend(jenna, scott);
        Friend friend3 = new Friend(jenna, naeem);

        dataLoaderFriendRepository.save(friend1);
        dataLoaderFriendRepository.save(friend2);
        dataLoaderFriendRepository.save(friend3);

    }

}