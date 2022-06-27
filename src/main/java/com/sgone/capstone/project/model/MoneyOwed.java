package com.sgone.capstone.project.model;

import javax.persistence.*;

@Entity
@Table(name = "money_owed")
public class MoneyOwed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "paid")
    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private ApplicationUser payee;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private ApplicationUser payer;

    public MoneyOwed() {}

    public MoneyOwed(Long id,
                     Double amount,
                     Boolean paid,
                     ApplicationUser payee,
                     ApplicationUser payer) {
        this.id = id;
        this.amount = amount;
        this.paid = paid;
        this.payee = payee;
        this.payer = payer;
    }

    public MoneyOwed(Double amount,
                     Boolean paid,
                     ApplicationUser payee,
                     ApplicationUser payer) {
        this.amount = amount;
        this.paid = paid;
        this.payee = payee;
        this.payer = payer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
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
}
