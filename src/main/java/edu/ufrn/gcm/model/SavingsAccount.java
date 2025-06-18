package edu.ufrn.gcm.model;

public class SavingsAccount extends AccountModel {

    public SavingsAccount(String number, Double total) {
        super(number, total);
    }

    public void renderInterest(double percentageRate) {
        double interest = getTotal() * (percentageRate / 100);
        setTotal(getTotal() + interest);
    }
}
