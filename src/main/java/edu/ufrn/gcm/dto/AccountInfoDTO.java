package edu.ufrn.gcm.dto;

public class AccountInfoDTO {
    private String type;
    private String number;
    private Double balance;
    private Integer score;

    public AccountInfoDTO(String type, String number, Double balance, Integer score) {
        this.type = type;
        this.number = number;
        this.balance = balance;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public Integer getScore() {
        return score;
    }
}
