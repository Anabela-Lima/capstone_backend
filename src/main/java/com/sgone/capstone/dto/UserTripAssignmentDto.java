package com.sgone.capstone.dto;

/**
 *  This DTO is a part of a response to the frontend
 *  and should be inside an array of users assigned
 *  to a particular trip.
 *
 *  JSON Response: <Trip>
 *  {
 *      id: 1,
 *      name: "trip",
 *      ...
 *      tripAssignments: [
 *          UserTripAssignmentDto user1,
 *          UserTripAssignmentDto user2,
 *          UserTripAssignmentDto user3,
 *      ],
 *      ...
 *  }
 */

public class UserTripAssignmentDto {

    private Long id;
    private String username;
    private String email;
    private Long mobile;
    private String firstname;
    private String lastname;


    public UserTripAssignmentDto(Long id,
                                 String username,
                                 String email,
                                 Long mobile,
                                 String firstname,
                                 String lastname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
