package com.sgone.capstone.dataloader;

import com.sgone.capstone.dataloader.repository.*;
import com.sgone.capstone.project.model.*;
import com.sgone.capstone.project.model.Enum.DayActivityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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
                "anaLima_1",
                passwordEncoder.encode("password"),
                "ana@email.com",
                1L,
                "Ana",
                "Lima",
                false,
                false,
                "UK",
                "London"
        );

        ApplicationUser jenna = new ApplicationUser(
                "jennaV771",
                passwordEncoder.encode("password"),
                "jenna@email.com",
                2L,
                "Jenna",
                "Vlahos",
                false,
                false,
                "UK",
                "London"
        );

        ApplicationUser naeem = new ApplicationUser(
                "thelifeandtimesofnaeem",
                passwordEncoder.encode("password"),
                "naeem@email.com",
                3L,
                "Naeem",
                "Khan",
                false,
                false,
                "https://img.freepik.com/free-photo/closeup-portrait-smiling-handsome-asian-man_1262-3728.jpg?w=2000",
                "UK",
                "Liverpool",
                "Nothing feels as good as going from a place you \n" +
                        "                love to a place youv’e never been #CatchMeIfYouCan"
        );

        ApplicationUser scott = new ApplicationUser(
                "scottaccino123",
                passwordEncoder.encode("password"),
                "scott@email.com",
                4L,
                "Scott",
                "Christie",
                false,
                false,
                "UK",
                "Lake District"

        );

        ApplicationUser steve = new ApplicationUser(
                "stevo789",
                passwordEncoder.encode("password"),
                "steve@strangerthings.com",
                5L,
                "Steve",
                "Harrington",
                false,
                false
        );

        dataLoaderApplicationUserRepository.save(ana);
        dataLoaderApplicationUserRepository.save(jenna);
        dataLoaderApplicationUserRepository.save(naeem);
        dataLoaderApplicationUserRepository.save(scott);
        dataLoaderApplicationUserRepository.save(steve);

        Trip trip1 = new Trip(
                UUID.randomUUID().toString(),
                "trip1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(3),
                "trip1 description",
                "Portugal",
                "https://www.mickeyshannon.com/photos/moraine-lake-sunrise-brilliance.jpg"

        );


        dataLoaderTripRepository.save(trip1);


        TripAssignment tripAssignment1 = new TripAssignment(trip1, ana);
        TripAssignment tripAssignment2 = new TripAssignment(trip1, jenna);
        TripAssignment tripAssignment5 = new TripAssignment(trip1, scott);
        TripAssignment tripAssignment6 = new TripAssignment(trip1, naeem);

        dataLoaderTripAssignmentRepository.save(tripAssignment1);
        dataLoaderTripAssignmentRepository.save(tripAssignment2);
        dataLoaderTripAssignmentRepository.save(tripAssignment5);
        dataLoaderTripAssignmentRepository.save(tripAssignment6);

        Day trip1Day1 = new Day("trip_1_day_1", 200.00, LocalDateTime.now(), trip1);
        Day trip1Day2 = new Day("trip_1_day_2", 200.00, LocalDateTime.now().plusDays(1), trip1);

        dataLoaderDayRepository.save(trip1Day1);
        dataLoaderDayRepository.save(trip1Day2);

        DayActivity dayActivity1 = new DayActivity(
                "Pizza",
                "London",
                100.00,
                DayActivityType.valueOf("Food"),
                trip1Day1
        );

        DayActivity dayActivity2 = new DayActivity(
                "Pizza again",
                "London",
                100.00,
                DayActivityType.valueOf("Food"),
                trip1Day1
        );

        DayActivity dayActivity3 = new DayActivity(
                "Tennis",
                "London",
                100.00,
                DayActivityType.valueOf("Physical"),
                trip1Day1
        );

        DayActivity dayActivity4 = new DayActivity(
                "Tennis",
                "London",
                100.00,
                DayActivityType.valueOf("Physical"),
                trip1Day2
        );

        dataLoaderDayActivityRepository.save(dayActivity1);
        dataLoaderDayActivityRepository.save(dayActivity2);
        dataLoaderDayActivityRepository.save(dayActivity3);
        dataLoaderDayActivityRepository.save(dayActivity4);

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

        DayActivityAssignment dayActivityAssignment5 = new DayActivityAssignment(
                0.00,
                33.00,
                dayActivity2,
                ana
        );
        DayActivityAssignment dayActivityAssignment6 = new DayActivityAssignment(
                0.00,
                33.00,
                dayActivity2,
                jenna
        );
        DayActivityAssignment dayActivityAssignment7 = new DayActivityAssignment(
                0.00,
                34.00,
                dayActivity2,
                naeem
        );

        DayActivityAssignment dayActivityAssignment8 = new DayActivityAssignment(
                0.00,
                34.00,
                dayActivity3,
                jenna
        );

        DayActivityAssignment dayActivityAssignment9 = new DayActivityAssignment(
                0.00,
                33.00,
                dayActivity3,
                naeem
        );

        DayActivityAssignment dayActivityAssignment10 = new DayActivityAssignment(
                0.00,
                33.00,
                dayActivity3,
                ana
        );

        DayActivityAssignment dayActivityAssignment11 = new DayActivityAssignment(
                0.00,
                75.00,
                dayActivity4,
                ana
        );

        DayActivityAssignment dayActivityAssignment12 = new DayActivityAssignment(
                0.00,
                25.00,
                dayActivity4,
                naeem
        );

        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment1);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment2);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment3);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment4);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment5);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment6);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment7);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment8);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment9);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment10);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment11);
        dataLoaderDayActivityAssignmentRepository.save(dayActivityAssignment12);



        Friend friend1 = new Friend(jenna, ana, jenna.getUsername(),ana.getUsername());
        Friend friend2 = new Friend(jenna, scott, jenna.getUsername(), scott.getUsername());
        Friend friend3 = new Friend(jenna, naeem, jenna.getUsername(), naeem.getUsername());

        dataLoaderFriendRepository.save(friend1);
        dataLoaderFriendRepository.save(friend2);
        dataLoaderFriendRepository.save(friend3);
    }

}