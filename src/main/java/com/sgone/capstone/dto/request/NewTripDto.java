package com.sgone.capstone.dto.request;


import java.time.LocalDateTime;

public class NewTripDto {

    private Long userId;
    private String name;
    private String country;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public NewTripDto() {}

    public NewTripDto(Long userId,
                      String name,
                      String country,
                      String description,
                      LocalDateTime startDate,
                      LocalDateTime endDate) {
        this.userId = userId;
        this.name = name;
        this.country = country;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
