package com.sgone.capstone.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "img_url")
    private String imgURL;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "profile_description")
    private String profileDescription;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TripAssignment> tripAssignments;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DayActivityAssignment> dayActivityAssignments;

    @OneToMany(mappedBy = "friend_a", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Friend> friends_a;

    @OneToMany(mappedBy = "friend_b", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Friend> friends_b;
    // TODO: User Entity properties goes here

    public ApplicationUser() {}

    // TODO: Modify constructor after adding additional properties

    public ApplicationUser(String username,
                           String password,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Boolean isAdmin,
                           Boolean isOwner,
                           String imgURL,
                           String country,
                           String city
    ) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imgURL = imgURL;
        this.country = country;
        this.city = city;
    }

    public ApplicationUser(String username,
                           String password,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Boolean isAdmin,
                           Boolean isOwner,
                           String imgURL,
                           String country,
                           String city,
                           String profileDescription
    ) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imgURL = imgURL;
        this.country = country;
        this.city = city;
        this.profileDescription = profileDescription;
    }



    public ApplicationUser(Long id, String username, String password, Boolean isAdmin,
                           Boolean isOwner, String email, Long mobile, String firstname,
                           String lastname, String imgURL, String country, String city,
                           Set<TripAssignment> tripAssignments, Set<DayActivityAssignment> dayActivityAssignments,
                           Set<Friend> friends_a, Set<Friend> friends_b) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imgURL = imgURL;
        this.country = country;
        this.city = city;
        this.tripAssignments = tripAssignments;
        this.dayActivityAssignments = dayActivityAssignments;
        this.friends_a = friends_a;
        this.friends_b = friends_b;
    }

    // TODO: Modify constructor after adding additional properties


    public ApplicationUser(String username,
                           String password,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Boolean isAdmin,
                           Boolean isOwner,
                           String country,
                           String city
    ) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imgURL = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
        this.country = country;
        this.city = city;
    }


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
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imgURL = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
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

    public Set<TripAssignment> getTripAssignments() {
        return tripAssignments;
    }

    public void setTripAssignments(Set<TripAssignment> tripAssignments) {
        this.tripAssignments = tripAssignments;
    }

    public Set<DayActivityAssignment> getDayActivityAssignments() {
        return dayActivityAssignments;
    }

    public void setDayActivityAssignments(Set<DayActivityAssignment> dayActivityAssignments) {
        this.dayActivityAssignments = dayActivityAssignments;
    }

    public Set<Friend> getFriends_a() {
        return friends_a;
    }

    public void setFriends_a(Set<Friend> friends_a) {
        this.friends_a = friends_a;
    }

    public Set<Friend> getFriends_b() {
        return friends_b;
    }

    public void setFriends_b(Set<Friend> friends_b) {
        this.friends_b = friends_b;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}
