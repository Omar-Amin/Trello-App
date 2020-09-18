package com.omarlet.trelloapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omarlet.trelloapp.model.Board;

import java.util.ArrayList;

public class BoardRecyclerView extends RecyclerView.Adapter<BoardRecyclerView.BoardViewHolder>{

    private Context context;
    private ArrayList<Board> boards;

    public BoardRecyclerView(Context context, ArrayList<Board> boards){
        this.context = context;
        this.boards = boards;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    static class BoardViewHolder extends RecyclerView.ViewHolder{

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
