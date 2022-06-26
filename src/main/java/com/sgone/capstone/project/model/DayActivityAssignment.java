package com.sgone.capstone.project.model;


import javax.persistence.*;

@Entity
@Table(name = "day_activity_assignment")
public class DayActivityAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paid")
    private Double paid;
    @Column(name = "outstanding")
    private Double outstanding;

    @ManyToOne
    @JoinColumn(name = "day_activity_id")
    private DayActivity dayActivity;

    @ManyToOne
    @JoinColumn(name = "application_user_id")
    private ApplicationUser applicationUser;

    public DayActivityAssignment() {}

    public DayActivityAssignment(Long id,
                                 Double paid,
                                 Double outstanding,
                                 DayActivity dayActivity,
                                 ApplicationUser applicationUser) {
        this.id = id;
        this.paid = paid;
        this.outstanding = outstanding;
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

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
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
