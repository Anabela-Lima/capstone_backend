package com.sgone.capstone.project.model;

public class TripPieChart {
    private Long tripID;
    private Double overallPercentageOfBudget;
    private Double foodPercentage;
    private Double physicalPercentage;
    private Double entertainmentPercentage;
    private Double travelPercentage;
    private Double otherPercentage;

    public TripPieChart() {
    }

    public TripPieChart(Long tripID, Double overallPercentageOfBudget, Double foodPercentage,
                        Double physicalPercentage, Double entertainmentPercentage, Double travelPercentage,
                        Double otherPercentage) {
        this.tripID = tripID;
        this.overallPercentageOfBudget = overallPercentageOfBudget;
        this.foodPercentage = foodPercentage;
        this.physicalPercentage = physicalPercentage;
        this.entertainmentPercentage = entertainmentPercentage;
        this.travelPercentage = travelPercentage;
        this.otherPercentage = otherPercentage;
    }

    public Long getTripID() {
        return tripID;
    }

    public void setTripID(Long tripID) {
        this.tripID = tripID;
    }

    public Double getOverallPercentageOfBudget() {
        return overallPercentageOfBudget;
    }

    public void setOverallPercentageOfBudget(Double overallPercentageOfBudget) {
        this.overallPercentageOfBudget = overallPercentageOfBudget;
    }

    public Double getFoodPercentage() {
        return foodPercentage;
    }

    public void setFoodPercentage(Double foodPercentage) {
        this.foodPercentage = foodPercentage;
    }

    public Double getPhysicalPercentage() {
        return physicalPercentage;
    }

    public void setPhysicalPercentage(Double physicalPercentage) {
        this.physicalPercentage = physicalPercentage;
    }

    public Double getEntertainmentPercentage() {
        return entertainmentPercentage;
    }

    public void setEntertainmentPercentage(Double entertainmentPercentage) {
        this.entertainmentPercentage = entertainmentPercentage;
    }

    public Double getTravelPercentage() {
        return travelPercentage;
    }

    public void setTravelPercentage(Double travelPercentage) {
        this.travelPercentage = travelPercentage;
    }

    public Double getOtherPercentage() {
        return otherPercentage;
    }

    public void setOtherPercentage(Double otherPercentage) {
        this.otherPercentage = otherPercentage;
    }
}
