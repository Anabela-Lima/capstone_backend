package com.sgone.capstone.project.model;

import java.util.Map;

public class PayeeAndPayer {

    private Long payee;
    private Long payer;
    private Long owed;

    public PayeeAndPayer() {
    }

    public PayeeAndPayer(Long payee, Long payer, Long owed) {
        this.payee = payee;
        this.payer = payer;
        this.owed = owed;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Long getOwed() {
        return owed;
    }

    public void setOwed(Long owed) {
        this.owed = owed;
    }
}
