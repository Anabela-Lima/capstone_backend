package com.sgone.capstone.dto;

import java.util.Set;

public class CustomApplicationUserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Long mobile;
    private String firstname;
    private String lastname;
    private Set<Long> tripAssignmentId;

    public CustomApplicationUserDto(Long id,
                                    String username,
                                    String password,
                                    String email,
                                    Long mobile,
                                    String firstname,
                                    String lastname,
                                    Set<Long> tripAssignmentId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tripAssignmentId = tripAssignmentId;
    }

    public CustomApplicationUserDto(Long id, String username, String password, String email, Long mobile, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Long> getTripAssignmentId() {
        return tripAssignmentId;
    }

    public void setTripAssignmentId(Set<Long> tripAssignmentId) {
        this.tripAssignmentId = tripAssignmentId;
    }
}