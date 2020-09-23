package com.omarlet.trelloapp.model;

import java.util.ArrayList;

public class List {

    private ArrayList<Card> cards;
    private String name;

    public List(){
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
}
