package com.excel.vo;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class User {

    private String username;
    private String cardNumber;
    private String date;
    private String money;
    private String status;

    public User(){

    }

    public User(String username, String cardNumber, String date, String money, String status) {
        this.username = username;
        this.cardNumber = cardNumber;
        this.date = date;
        this.money = money;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", date='" + date + '\'' +
                ", money='" + money + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
