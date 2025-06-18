package edu.ufrn.gcm.model;

public class AccountModel {
    private String number;
    private Double total;

    public AccountModel(String number, Double total) {
        this.number = number;
        this.total = total;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
}
