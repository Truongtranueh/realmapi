package com.example.realmapi.Model;

public class Product {
    private int id;
    private String name;
    private String img;
    private String style;


    public Product(int id, String name, String img, String style) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.img = img;

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
