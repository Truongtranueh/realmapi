package com.example.realmapi.Model;

import java.time.LocalDateTime;
import java.util.Date;

public class History {
    private String type;
    private String title;
    private Date date;
    private int money;

    public History(String type, String title, Date date, int money) {
        this.type = type;
        this.title = title;
        this.date = date;
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
