package com.omarlet.trelloapp.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Board implements Serializable {

    private String name, desc, url, pictureUrl;

    public Board(String name, String desc, String url, String pictureUrl){
        this.name = name;
        this.desc = desc;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + name + " \nDesc: " + desc + " \nUrl: " + url + "\nPicture: " + pictureUrl;
    }

}
