package com.omarlet.trelloapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omarlet.trelloapp.R;
import com.omarlet.trelloapp.model.Card;

import java.util.ArrayList;

public class CardRecyclerView extends RecyclerView.Adapter<CardRecyclerView.CardViewHolder> {

    private Context context;
    private ArrayList<Card> cards;

    public CardRecyclerView(Context context, ArrayList<Card> cards){
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_content_layout,parent,false);
        return new CardRecyclerView.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.contentDescription.setText(card.getName());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder{

        // TODO: Maybe add more fields to the cards
        TextView contentDescription;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            contentDescription = itemView.findViewById(R.id.contentDescription);
        }

    }

}
