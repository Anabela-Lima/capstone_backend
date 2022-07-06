package com.sgone.capstone.project.model;

import javax.persistence.*;


@Entity
@Table(name = "money_owed")
public class MoneyOwed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owed")
    private Long owed;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private ApplicationUser payee;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private ApplicationUser payer;

    @ManyToOne
    @JoinColumn(name="trip_id")
    private Trip trip;

    public MoneyOwed() {}

    public MoneyOwed(Long id, Long owed, ApplicationUser payee, ApplicationUser payer, Trip trip) {
        this.id = id;
        this.owed = owed;
        this.payee = payee;
        this.payer = payer;
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwed() {
        return owed;
    }

    public void setOwed(Long owed) {
        this.owed = owed;
    }

    public ApplicationUser getPayee() {
        return payee;
    }

    public void setPayee(ApplicationUser payee) {
        this.payee = payee;
    }

    public ApplicationUser getPayer() {
        return payer;
    }

    public void setPayer(ApplicationUser payer) {
        this.payer = payer;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}