package com.sgone.capstone.project.model;



import com.google.common.collect.Sets;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

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

    @OneToMany(mappedBy = "trip")
    private Set<TripAssignment> tripAssignments;

    @OneToMany(mappedBy = "trip")
    private Set<Day> days;

    public Trip() {}

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
                String country) {
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.tripAssignments = Sets.newHashSet();
        this.days = Sets.newHashSet();
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
}
