package com.sgone.capstone.dto;

import com.sgone.capstone.project.model.Day;
import com.sgone.capstone.project.model.Enum.DayActivityType;

public class CustomDayActivityDto {

    private Long id;
    private String name;
    private String location;
    private Double price;
    private String dayActivityType;
    private Day day;

    public CustomDayActivityDto(Long id, String name, String location, Double price, String dayActivityType, Day day) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.dayActivityType = dayActivityType;
        this.day = day;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDayActivityType() {
        return dayActivityType;
    }

    public void setDayActivityType(String dayActivityType) {
        this.dayActivityType = dayActivityType;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
