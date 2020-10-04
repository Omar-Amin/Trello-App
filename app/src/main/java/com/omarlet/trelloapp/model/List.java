package com.omarlet.trelloapp.model;

import java.util.ArrayList;

public class List {

    private ArrayList<Card> cards;
    private String name;
    private String id;

    public List(String id){
        this.id = id;
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId(){
        return id;
    }
}
