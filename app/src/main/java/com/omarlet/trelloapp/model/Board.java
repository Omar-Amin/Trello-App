package com.omarlet.trelloapp.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Board implements Serializable {

    private String name, desc, id, pictureUrl;

    public Board(String name, String desc, String id, String pictureUrl){
        this.name = name;
        this.desc = desc;
        this.id = id;
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + " \nDesc: " + desc + " \nUrl: " + id + "\nPicture: " + pictureUrl;
    }

}
