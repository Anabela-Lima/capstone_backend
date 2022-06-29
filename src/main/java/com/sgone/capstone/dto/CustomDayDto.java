package com.sgone.capstone.dto;

import java.time.LocalDateTime;

public class CustomDayDto {

    private Long id;
    private String name;
    private Double budget;
    private LocalDateTime date;

    public CustomDayDto(Long id,
                        String name,
                        Double budget,
                        LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.date = date;
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
}
