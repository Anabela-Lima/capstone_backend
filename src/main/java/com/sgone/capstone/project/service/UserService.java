package com.sgone.capstone.project.service;

import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.repository.TripAssignmentRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.thymeleaf.util.StringUtils.concat;

@Service
public class UserService {

    private UserRepository userRepository;
    private TripRepository tripRepository;
    private TripAssignmentRepository tripAssignmentRepository;

    public UserService() {}

    @Autowired
    public UserService(UserRepository userRepository,
                       TripRepository tripRepository,
                       TripAssignmentRepository tripAssignmentRepository) {
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.tripAssignmentRepository = tripAssignmentRepository;
    }


    public Trip getTrip() {
        return null;
    }


    public Trip createTrip(NewTripDto newTripDto) {

        Optional<ApplicationUser> userOptional =
                userRepository.getAllUsers(newTripDto.getUserId());

        if (!userOptional.isPresent()){
            throw new RuntimeException("User does not exist!");
        }

        ApplicationUser user = userOptional.get();

        String tripCode = UUID.randomUUID().toString();

        Trip newTrip = new Trip(
                tripCode,
                newTripDto.getName(),
                newTripDto.getStartDate(),
                newTripDto.getEndDate(),
                newTripDto.getDescription(),
                newTripDto.getCountry()
        );

        tripRepository.save(newTrip);
        TripAssignment tripAssignment = new TripAssignment(newTrip, user);
        tripAssignmentRepository.save(tripAssignment);

        return newTrip;
    }


    public Trip addFriendToTrip() {
        return null;
    }

    public String findUserByFirstAndLastNames(String inputFirstName, String inputLastName){

        String targetUserFirstName = userRepository.findAll().stream()
                .map(user -> user.getFirstname().toLowerCase())
                        .filter(s -> s.contains(inputFirstName.toLowerCase())).toString();

        String targetUserLastName = userRepository.findAll().stream()
                .map(user -> user.getLastname().toLowerCase())
                .filter(s -> s.contains(inputLastName.toLowerCase())).toString();


//        if (targetUserFirstName.isEmpty()) {
//            ArrayList<String> noMatches = new ArrayList<>();
//            noMatches.add("No users found :(");
//            return noMatches;
//        }


        return concat(targetUserFirstName + targetUserLastName);



    }

}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend