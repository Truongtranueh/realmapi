package com.example.realmapi.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Room extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private int celsius;
    private String floor;
    private int humidity;
    private String img;

    public Room() {

    }

    public Room(int id, String name, String img, int humidity, int celsius, String floor) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.celsius = celsius;
        this.humidity = humidity;
        this.floor = floor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCelsius() {
        return celsius;
    }

    public void setCelsius(int celsius) {
        this.celsius = celsius;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
