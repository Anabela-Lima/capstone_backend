package com.sgone.capstone.project.service;

import com.sgone.capstone.dataloader.DataLoaderUsersArray;
import com.sgone.capstone.dataloader.repository.DataLoaderApplicationUserRepository;
import com.sgone.capstone.dto.request.NewTripDto;
import com.sgone.capstone.project.model.ApplicationUser;
import com.sgone.capstone.project.model.Trip;
import com.sgone.capstone.project.model.TripAssignment;
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

    public Optional<ApplicationUser> getUserByName(String firstname) {

        Optional<ApplicationUser> targetUser = userManagementRepository.getUserByName(firstname);

        return targetUser;
    }

}


//    public Trip getTrip() {
//        return null;
//
//        // get trip by tripCode
//
//        public Trip getTrip(String tripCode){
//
//            System.out.println(tripCode);
//            Optional<Trip> tripOptional = tripRepository.findByTripCode(tripCode);
//
//            if (!tripOptional.isPresent()) {
//                throw new RuntimeException("trip code is invalid, please try again!");
//            }
//
//            System.out.println(tripOptional.get());
//
//            return tripOptional.get();
//
//
//        }
//
//
//        // get all trips
//
////        public List<Trip> getAllTrips () {
////            return tripRepository.findAll();
////        }
//
//
//        // create Trip
//
//        public Trip createTrip (NewTripDto newTripDto){
//
//            Optional<ApplicationUser> userOptional =
//                    userRepository.getAllUsers(newTripDto.getUserId());
//
//            if (!userOptional.isPresent()) {
//                throw new RuntimeException("User does not exist!");
//            }
//
//            ApplicationUser user = userOptional.get();
//
//            String tripCode = UUID.randomUUID().toString();
//
//            Trip newTrip = new Trip(
//                    tripCode,
//                    newTripDto.getName(),
//                    newTripDto.getStartDate(),
//                    newTripDto.getEndDate(),
//                    newTripDto.getDescription(),
//                    newTripDto.getCountry()
//            );
//
//            tripRepository.save(newTrip);
//            TripAssignment tripAssignment = new TripAssignment(newTrip, user);
//            tripAssignmentRepository.save(tripAssignment);
//
//            return newTrip;
//        }
//
//
//        // add friend
//        public Trip addFriendToTrip () {
//            return null;
//        }
//
//
////        if (targetUserFirstName.isEmpty()) {
////            ArrayList<String> noMatches = new ArrayList<>();
////            noMatches.add("No users found :(");
////            return noMatches;
////        }
//
//
//            public List<Product> returnRelevantProducts ( int stockRequired, String manufacturer, String model, Double
//            minPrice, Double maxPrice) throws Exception {
//
//                if (minPrice > maxPrice) {
//                    throw new Exception("Minimum price must be lower than maximum price!");
//                }
//
//                List<Product> inStock = productRepository.findProductsMinStock(stockRequired);
//                List<Product> priceRange = productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
//                List<Product> result = inStock.stream().filter(priceRange::contains).collect(Collectors.toList());
//                if (manufacturer != null) {
//                    List<Product> byManufacturer = productRepository.findByManufacturerContainingIgnoreCase(manufacturer.trim());
//                    if (byManufacturer.isEmpty()) {
//                        throw new Exception("No cars by this manufacturer stocked!");
//                    }
//                    result = result.stream().filter(o -> byManufacturer.contains(o)).collect(Collectors.toList());
//                }
//                if (model != null) {
//                    List<Product> byModel = productRepository.findByModelContainingIgnoreCase(model.trim());
//                    if (byModel.isEmpty()) {
//                        throw new Exception("Model not stocked!");
//                    }
//                    result = result.stream().filter(byModel::contains).collect(Collectors.toList());
//                }
//                if (inStock.isEmpty()) {
//                    throw new Exception("No cars in stock!");
//                }
//                if (priceRange.isEmpty()) {
//                    throw new Exception("No cars in this price range!");
//                }
//                if (result.isEmpty()) {
//                    throw new Exception("No cars found to meet this criteria!");
//                }
//                return result;
//            }
//
//
//        }
//
//    }

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend