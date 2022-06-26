package com.sgone.capstone.project.model;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password_hash", nullable = false)
    private String password;
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;
    @Column(name = "is_owner", nullable = false)
    private Boolean isOwner;


    // TODO: User Entity properties goes here
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "mobile", unique = true)
    private Long mobile;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "applicationUser")
    private Set<TripAssignement> tripAssignements;

    @OneToMany(mappedBy = "applicationUser")
    private Set<DayActivityAssignment> dayActivityAssignments;
    // TODO: User Entity properties goes here

    public ApplicationUser() {}

    // TODO: Modify constructor after adding additional properties

    public ApplicationUser(Long id,
                           String username,
                           String password,
                           Boolean isAdmin,
                           Boolean isOwner,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Set<TripAssignement> tripAssignements,
                           Set<DayActivityAssignment> dayActivityAssignments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tripAssignements = tripAssignements;
        this.dayActivityAssignments = dayActivityAssignments;
    }


    // TODO: Modify constructor after adding additional properties


    public ApplicationUser(String username,
                           String password,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Boolean isAdmin,
                           Boolean isOwner
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.tripAssignements = Sets.newHashSet();
        this.dayActivityAssignments = Sets.newHashSet();
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
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

    public Set<TripAssignement> getTripAssignements() {
        return tripAssignements;
    }

    public void setTripAssignements(Set<TripAssignement> tripAssignements) {
        this.tripAssignements = tripAssignements;
    }

    public Set<DayActivityAssignment> getDayActivityAssignments() {
        return dayActivityAssignments;
    }

    public void setDayActivityAssignments(Set<DayActivityAssignment> dayActivityAssignments) {
        this.dayActivityAssignments = dayActivityAssignments;
    }
}
