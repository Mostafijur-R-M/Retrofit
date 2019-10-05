package com.fendonus.retrofit.model;

import com.jaedongchicken.ytplayer.YoutubePlayerView;

public class Parent {
    String CardName;
    double Minimum_salary, Interest_Rate;

    public Parent() {
    }

    public Parent(String cardName, double minimum_salary, double interest_Rate) {
        CardName = cardName;
        Minimum_salary = minimum_salary;
        Interest_Rate = interest_Rate;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public double getMinimum_salary() {
        return Minimum_salary;
    }

    public void setMinimum_salary(double minimum_salary) {
        Minimum_salary = minimum_salary;
    }

    public double getInterest_Rate() {
        return Interest_Rate;
    }

    public void setInterest_Rate(double interest_Rate) {
        Interest_Rate = interest_Rate;
    }
}
