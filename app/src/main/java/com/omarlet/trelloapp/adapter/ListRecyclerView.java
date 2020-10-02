package com.omarlet.trelloapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omarlet.trelloapp.R;
import com.omarlet.trelloapp.model.List;
import com.omarlet.trelloapp.ui.AddCardDialog;

import java.util.ArrayList;

public class ListRecyclerView extends RecyclerView.Adapter<ListRecyclerView.ListViewHolder>{

    private Context context;
    private ArrayList<List> lists;

    public ListRecyclerView(Context context, ArrayList<List> lists){
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.board_content,parent,false);
        return new ListRecyclerView.ListViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        // TODO: Change the title so it matches the list
        String name = lists.get(position).getName();
        holder.listTitle.setText(name);

        RecyclerView rc = holder.listContent;
        LinearLayoutManager lm = new LinearLayoutManager(context);
        rc.setLayoutManager(lm);
        rc.setAdapter(new CardRecyclerView(context,lists.get(position).getCards()));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder{

        TextView listTitle;
        RecyclerView listContent;
        Button addCard;

        public ListViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            listTitle = itemView.findViewById(R.id.listTitle);
            listContent = itemView.findViewById(R.id.listContent);
            addCard = itemView.findViewById(R.id.addCard);

            addCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AddCardDialog(context).show();
                }
            });
        }

    }

}
