package com.sgone.capstone.project.model;



import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "number_of_days", nullable = false)
    private Integer numberOfDays;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "trip")
    private Set<TripAssignment> tripAssignments;

    @OneToMany(mappedBy = "trip")
    private Set<Day> days;

    public Trip() {}

    public Trip(Long id,
                String name,
                Integer numberOfDays,
                String description,
                Set<TripAssignment> tripAssignments,
                Set<Day> days) {
        this.id = id;
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.description = description;
        this.tripAssignments = tripAssignments;
        this.days = days;
    }

    public Trip(String name,
                Integer numberOfDays,
                String description) {
        this.name = name;
        this.numberOfDays = numberOfDays;
        this.description = description;
        this.tripAssignments = Sets.newHashSet();
        this.days = Sets.newHashSet();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TripAssignment> getTripAssignments() {
        return tripAssignments;
    }

    public void setTripAssignements(Set<TripAssignment> tripAssignements) {
        this.tripAssignments = tripAssignements;
    }

    public Set<Day> getDays() {
        return days;
    }

    public void setDays(Set<Day> days) {
        this.days = days;
    }
}
