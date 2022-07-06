package com.sgone.capstone.project.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "budget")
    private Double budget;
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DayActivity> dayActivities;

    public Day() {}

    public Day(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Day(Long id,
               String name,
               Double budget,
               LocalDateTime date,
               Trip trip,
               Set<DayActivity> dayActivities) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.date = date;
        this.trip = trip;
        this.dayActivities = dayActivities;
    }

    public Day(String name,
               Double budget,
               LocalDateTime date,
               Trip trip) {
        this.name = name;
        this.budget = budget;
        this.date = date;
        this.trip = trip;
        this.dayActivities = Sets.newHashSet();
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Set<DayActivity> getDayActivities() {
        return dayActivities;
    }

    public void setDayActivities(Set<DayActivity> dayActivities) {
        this.dayActivities = dayActivities;
    }
}
