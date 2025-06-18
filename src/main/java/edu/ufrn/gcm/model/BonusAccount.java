package edu.ufrn.gcm.model;

public class BonusAccount extends AccountModel{
    private Integer score;

    public BonusAccount(String number, Double total) {
        super(number, total);
        this.score = 10;
    }

    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

    public void increaseScore(Integer score){
        this.score += score;

    }
}
