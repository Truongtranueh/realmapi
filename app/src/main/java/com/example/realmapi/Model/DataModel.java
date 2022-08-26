package com.example.realmapi.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModel extends RealmObject {

    @PrimaryKey
    private int id;
    private int userId;
    private String title;
    private String image;
    private String body;

    public DataModel() {
    }

    public DataModel(int id, int userId, String title,String image, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.image = image;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
