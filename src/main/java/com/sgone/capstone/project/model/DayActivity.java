package com.sgone.capstone.project.model;

import com.google.common.collect.Sets;
import com.sgone.capstone.project.model.Enum.DayActivityType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "day_activity")
public class DayActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "location")
    private String location;
    @Column(name = "price")
    private Double price;
    @Column(name = "activity_type")
    private DayActivityType dayActivityType;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @OneToMany(mappedBy = "dayActivity")
    private Set<DayActivityAssignment> dayActivityAssignmentSet;


    public DayActivity() {}

    public DayActivity(Long id,
                       String name,
                       String location,
                       Double price,
                       DayActivityType dayActivityType,
                       Day day,
                       Set<DayActivityAssignment> dayActivityAssignmentSet) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.price = price;
        this.dayActivityType = dayActivityType;
        this.day = day;
        this.dayActivityAssignmentSet = dayActivityAssignmentSet;
    }

    public DayActivity(String name,
                       String location,
                       Double price,
                       DayActivityType dayActivityType,
                       Day day) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.dayActivityType = dayActivityType;
        this.day = day;
        this.dayActivityAssignmentSet = Sets.newHashSet();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DayActivityType getDayActivityType() {
        return dayActivityType;
    }

    public void setDayActivityType(DayActivityType dayActivityType) {
        this.dayActivityType = dayActivityType;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}