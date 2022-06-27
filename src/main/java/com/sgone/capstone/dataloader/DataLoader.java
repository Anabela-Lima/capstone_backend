package com.sgone.capstone.dataloader;

import com.sgone.capstone.dataloader.repository.*;
import com.sgone.capstone.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@Component
public class DataLoader implements ApplicationRunner {

    // Repository dependency goes here
    private DataLoaderApplicationUserRepository dataLoaderApplicationUserRepository;
    private DataLoaderTripRepository dataLoaderTripRepository;
    private DataLoaderTripAssignmentRepository dataLoaderTripAssignmentRepository;
    private DataLoaderDayRepository dataLoaderDayRepository;
    private DataLoaderDayActivityRepository dataLoaderDayActivityRepository;
    private DataLoaderDayActivityAssignmentRepository dataLoaderDayActivityAssignmentRepository;
    private DataLoaderFriendRepository dataLoaderFriendRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(){}

    @Autowired
    public DataLoader(DataLoaderApplicationUserRepository dataLoaderApplicationUserRepository,
                      DataLoaderTripRepository dataLoaderTripRepository,
                      DataLoaderTripAssignmentRepository dataLoaderTripAssignmentRepository,
                      DataLoaderDayRepository dataLoaderDayRepository,
                      DataLoaderDayActivityRepository dataLoaderDayActivityRepository,
                      DataLoaderDayActivityAssignmentRepository dataLoaderDayActivityAssignmentRepository,
                      DataLoaderFriendRepository dataLoaderFriendRepository,
                      PasswordEncoder passwordEncoder) {
        this.dataLoaderApplicationUserRepository = dataLoaderApplicationUserRepository;
        this.dataLoaderTripRepository = dataLoaderTripRepository;
        this.dataLoaderTripAssignmentRepository = dataLoaderTripAssignmentRepository;
        this.dataLoaderDayRepository = dataLoaderDayRepository;
        this.dataLoaderDayActivityRepository = dataLoaderDayActivityRepository;
        this.dataLoaderDayActivityAssignmentRepository = dataLoaderDayActivityAssignmentRepository;
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
                UUID.randomUUID().toString(),
                "trip1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(3),
                "trip1 description",
                "Portugal"
        );

        Trip trip2 = new Trip(
                UUID.randomUUID().toString(),
                "trip2",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(6),
                "trip2 description",
                "Dubai"
        );

        Trip trip3 = new Trip(
                UUID.randomUUID().toString(),
                "trip3",
                LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(10),
                "trip3 description",
                "Cyprus"
        );

        Trip trip4 = new Trip(
                UUID.randomUUID().toString(),
                "trip4",
                LocalDateTime.now().plusDays(11),
                LocalDateTime.now().plusDays(15),
                "trip4 description",
                "Germany"
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

        DayActivity dayActivity1 = new DayActivity(
                "pizza with the boys",
                "London",
                100.00,
                null,
                trip1Day1
        );

        dataLoaderDayActivityRepository.save(dayActivity1);

        DayActivityAssignment dayActivityAssignment1 = new DayActivityAssignment(
                0.00,
                25.00,
                dayActivity1,
                naeem
        );
        DayActivityAssignment dayActivityAssignment2 = new DayActivityAssignment(
                0.00,
                25.00,
                dayActivity1,
                scott
        );
        DayActivityAssignment dayActivityAssignment3 = new DayActivityAssignment(
                0.00,
                25.00,
                dayActivity1,
                jenna
        );
        DayActivityAssignment dayActivityAssignment4 = new DayActivityAssignment(
                0.00,
                25.00,
                dayActivity1,
                ana
        );

        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment1);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment2);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment3);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment4);


        Friend friend1 = new Friend(jenna, ana);
        Friend friend2 = new Friend(jenna, scott);
        Friend friend3 = new Friend(jenna, naeem);

        dataLoaderFriendRepository.save(friend1);
        dataLoaderFriendRepository.save(friend2);
        dataLoaderFriendRepository.save(friend3);

    }

}