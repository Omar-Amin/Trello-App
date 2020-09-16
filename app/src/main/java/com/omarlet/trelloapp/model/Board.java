package com.omarlet.trelloapp.model;

public class Board {

    private String name, desc, url;

    public Board(String name, String desc, String url){
        this.name = name;
        this.desc = desc;
        this.url = url;
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
}
