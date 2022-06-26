package com.sgone.capstone.project.model;

import com.sgone.capstone.project.model.Enum.DayActivityType;

import javax.persistence.*;

@Entity
@Table(name = "day_activity")
public class DayActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;
    @Column(name = "activity_type")
    private DayActivityType dayActivityType;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;


    public DayActivity() {}

    public DayActivity(Long id,
                       Double price,
                       DayActivityType dayActivityType,
                       Day day) {
        this.id = id;
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
