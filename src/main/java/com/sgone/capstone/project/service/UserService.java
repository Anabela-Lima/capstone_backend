package com.sgone.capstone.project.service;

import com.sgone.capstone.dataloader.DataLoaderUsersArray;
import com.sgone.capstone.dataloader.repository.DataLoaderApplicationUserRepository;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.TripAssignmentRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import com.sgone.capstone.security.management.user.UserManagementRepository;
import org.apache.tomcat.util.digester.ArrayStack;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.thymeleaf.util.StringUtils.concat;

import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripAssignmentRepository tripAssignmentRepository;

    @Autowired
    UserManagementRepository userManagementRepository;
    public UserService() {
    }

    @Autowired
    public UserService(UserRepository userRepository,
                       TripRepository tripRepository,
                       TripAssignmentRepository tripAssignmentRepository, UserManagementRepository userManagementRepository) {
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.tripAssignmentRepository = tripAssignmentRepository;
        this.userManagementRepository = userManagementRepository;
    }


//    public List<ApplicationUser> getUserByName(String firstname) {
//
//        List<ApplicationUser> targetUser = userRepository.getUserByName(firstname);
//
//        return targetUser;
//    }


    public Trip getTrip() {
        return null;
    }


    public Trip getTrip(String tripCode) {

        Optional<Trip> tripOptional = tripRepository.findByTripCode(tripCode);

        if(!tripOptional.isPresent()){
           throw new RuntimeException("Trip code is invalid, please try again!");
       }

       return tripOptional.get();
    }



    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }



    public Trip createTrip(NewTripDto newTripDto) {

        Optional<ApplicationUser> userOptional =
                userRepository.getUser(newTripDto.getUserId());

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

}


//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend