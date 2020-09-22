package com.omarlet.trelloapp.model;

import androidx.annotation.NonNull;

public class Card {

    private String idList, date, name;

    public Card(String idList, String date, String name){
        this.idList = idList;
        this.date = date;
        this.name = name;
    }


    public String getIdList() {
        return idList;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "IdList: " + idList + "\nDate: " + date + "\nName: " + name;
    }
}
