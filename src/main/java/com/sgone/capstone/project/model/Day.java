package com.sgone.capstone.project.model;


import javax.persistence.*;

@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "budget")
    private Double budget;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    public Day() {}

    public Day(Long id, String name, Double budget, Trip trip) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.trip = trip;
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

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
