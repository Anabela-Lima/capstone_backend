package com.sgone.capstone.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CustomTripDto {

    private Long id;
    private String tripCode;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String country;
    private List<UserTripAssignmentDto> users;

    public CustomTripDto(Long id,
                         String tripCode,
                         String name,
                         LocalDateTime startDate,
                         LocalDateTime endDate,
                         String description,
                         String country,
                         List<UserTripAssignmentDto> users) {
        this.id = id;
        this.tripCode = tripCode;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.country = country;
        this.users = users;
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

    public List<UserTripAssignmentDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserTripAssignmentDto> users) {
        this.users = users;
    }
}
