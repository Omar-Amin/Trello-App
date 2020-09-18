package com.omarlet.trelloapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.omarlet.trelloapp.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.board_layout,parent,false);
        return new BoardRecyclerView.BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        Board board = boards.get(position);
        holder.boardName.setText(board.getName());

        if (!board.getPictureUrl().equals("null")){
            Glide.with(context).load(board.getPictureUrl()).centerCrop().into(holder.boardPicture);
        }
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    static class BoardViewHolder extends RecyclerView.ViewHolder{

        TextView boardName;
        ImageView boardPicture;

        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            boardName = itemView.findViewById(R.id.boardName);
            boardPicture = itemView.findViewById(R.id.boardPicture);
        }

    }

}
