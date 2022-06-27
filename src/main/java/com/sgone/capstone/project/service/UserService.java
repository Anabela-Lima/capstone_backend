package com.sgone.capstone.project.service;

import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.repository.TripAssignmentRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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


    // get trip by tripCode

    public Trip getTrip(String tripCode) {

        System.out.println(tripCode);
       Optional <Trip> tripOptional = tripRepository.findByTripCode(tripCode);

       if(!tripOptional.isPresent()){
           throw new RuntimeException("trip code is invalid, please try again!");
       }

       System.out.println(tripOptional.get());

       return tripOptional.get();


    }


    // get all trips

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }


    // create Trip

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


    // add friend
    public Trip addFriendToTrip() {
        return null;
    }




}
