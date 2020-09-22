package com.omarlet.trelloapp.model;

import java.util.ArrayList;

public class List {

    private ArrayList<Card> cards;

    public List(){
        cards = new ArrayList<>();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

}
