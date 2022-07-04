package com.sgone.capstone.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "trip_code", nullable = false, unique = true)
    private String tripCode;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "description")
    private String description;
    @Column(name = "country")
    private String country;
    @Column(name = "img_url")
    private String imgURL;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private Set<TripAssignment> tripAssignments;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Day> days;

//    @ManyToMany(mappedBy = "trips", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<ApplicationUser> users;


    public Trip() {}

    public Trip(Long id, String tripCode, String name,
                LocalDateTime startDate, LocalDateTime endDate, String description,
                String country, String imgURL, Set<TripAssignment> tripAssignments, Set<Day> days) {
        this.id = id;
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.imgURL = imgURL;
        this.tripAssignments = tripAssignments;
        this.days = days;
    }

    public Trip(Long id,
                String tripCode,
                String name,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String description,
                String country,
                Set<TripAssignment> tripAssignments,
                Set<Day> days) {
        this.id = id;
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.tripAssignments = tripAssignments;
        this.days = days;
    }

    public Trip(String tripCode,
                String name,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String description,
                String country,
                String imgURL) {
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.imgURL = imgURL;
    }

    public Trip(String tripCode, String name, LocalDateTime startDate,
                LocalDateTime endDate, String description, String country) {
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.imgURL = "https://c.neh.tw/thumb/f/720/9be8f70458994300a81e.jpg";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<TripAssignment> getTripAssignments() {
        return tripAssignments;
    }

    public void setTripAssignments(Set<TripAssignment> tripAssignments) {
        this.tripAssignments = tripAssignments;
    }

    public Set<Day> getDays() {
        return days;
    }

    public void setDays(Set<Day> days) {
        this.days = days;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
