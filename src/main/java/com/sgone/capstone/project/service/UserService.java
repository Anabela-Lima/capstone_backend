package com.sgone.capstone.project.service;

import com.sgone.capstone.dto.CustomApplicationUserDto;
import com.sgone.capstone.dto.CustomTripDto;
import com.sgone.capstone.dto.UserTripAssignmentDto;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.TripAssignment;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.repository.DayRepository;
import com.sgone.capstone.project.repository.TripAssignmentRepository;
import com.sgone.capstone.project.repository.TripRepository;
import com.sgone.capstone.project.repository.UserRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.thymeleaf.util.StringUtils.concat;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripAssignmentRepository tripAssignmentRepository;
    @Autowired
    private DayRepository dayRepository;

    public UserService() {}


    public Trip getTrip() {
        return null;
    }


    public Trip getTrip(String tripCode) {

       Optional<Trip> tripOptional = tripRepository.findByTripCode(tripCode);

       if(!tripOptional.isPresent()){
           throw new RuntimeException("Trip code is invalid, please try again!");
       }

        /*
            TODO: switch to new CustomTripDto
         */

       return tripOptional.get();
    }



    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }



    public CustomTripDto createTrip(NewTripDto newTripDto) {

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
        Day firstDayOfTrip = new Day(
                "New Day",
                0.00,
                newTripDto.getStartDate(),
                newTrip
        );
        dayRepository.save(firstDayOfTrip);

        Trip trip = tripRepository.findByTripCode(tripCode).get();
        CustomTripDto customTripDto = new CustomTripDto(
                trip.getId(),
                trip.getTripCode(),
                trip.getName(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getDescription(),
                trip.getCountry(),
                getAllUsersByTripCode(trip.getTripCode())
        );

        return customTripDto;
    }


    public List<UserTripAssignmentDto> getAllUsersByTripCode(String tripCode) {

        List<ApplicationUser> applicationUsers =
                userRepository.getAllUsersByTripCode(tripCode);

        List<UserTripAssignmentDto> customApplicationUserDtos =
                applicationUsers
                        .stream()
                        .map(applicationUser -> {
                            return new UserTripAssignmentDto(
                                    applicationUser.getId(),
                                    applicationUser.getUsername(),
                                    applicationUser.getEmail(),
                                    applicationUser.getMobile(),
                                    applicationUser.getFirstname(),
                                    applicationUser.getLastname()
                            );
                        })
                        .collect(Collectors.toList());

        return customApplicationUserDtos;
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