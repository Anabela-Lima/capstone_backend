package com.sgone.capstone.project.model;


import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "day_activity_assignment")
public class DayActivityAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Paid 100
    @Column(name = "paid")
    private Double paid;
    // Should pay 20
    @Column(name = "should_pay")
    private Double shouldPay;


    @ManyToOne
    @JoinColumn(name = "day_activity_id")
    private DayActivity dayActivity;

    @ManyToOne
    @JoinColumn(name = "application_user_id")
    private ApplicationUser applicationUser;

    public DayActivityAssignment() {}

    public DayActivityAssignment(Long id) {
        this.id = id;
    }

    public DayActivityAssignment(Long id,
                                 Double paid,
                                 Double shouldPay,
                                 DayActivity dayActivity,
                                 ApplicationUser applicationUser) {
        this.id = id;
        this.paid = paid;
        this.shouldPay = shouldPay;
        this.dayActivity = dayActivity;
        this.applicationUser = applicationUser;
    }

    public DayActivityAssignment(Double paid,
                                 Double shouldPay,
                                 DayActivity dayActivity,
                                 ApplicationUser applicationUser) {
        this.paid = paid;
        this.shouldPay = shouldPay;
        this.dayActivity = dayActivity;
        this.applicationUser = applicationUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public Double getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(Double shouldPay) {
        this.shouldPay = shouldPay;
    }

    public DayActivity getDayActivity() {
        return dayActivity;
    }

    public void setDayActivity(DayActivity dayActivity) {
        this.dayActivity = dayActivity;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

}
