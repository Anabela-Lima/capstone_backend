package com.sgone.capstone.project.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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

//    @OneToMany(mappedBy = "trip")
//    private Set<TripAssignment> tripAssignments;
//
//    @OneToMany(mappedBy = "trip")
//    private Set<Day> days;

    @ManyToMany(mappedBy = "trips", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ApplicationUser> users;


    public Trip() {}

    public Trip(Long id,
                String tripCode,
                String name,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String description,
                String country,
                Set<ApplicationUser> users) {
        this.id = id;
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.users = users;
    }

    public Trip(String tripCode,
                String name,
                LocalDateTime startDate,
                LocalDateTime endDate,
                String description,
                String country) {
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.users = new HashSet<>();
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

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationUser> users) {
        this.users = users;
    }
}
