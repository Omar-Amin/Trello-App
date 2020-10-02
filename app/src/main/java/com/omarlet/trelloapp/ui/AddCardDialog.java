package com.omarlet.trelloapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.omarlet.trelloapp.R;

public class AddCardDialog extends Dialog {

    private Button addCard;
    private EditText cardContent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_layout);

        RequestQueue queue = Volley.newRequestQueue(context);

        addCard = findViewById(R.id.addNewCard);
        cardContent = findViewById(R.id.addCardContent);

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add post request to the card and update the list
                String content = cardContent.getText().toString();
                if (!content.isEmpty()){
                    //Post to trello
                    System.out.println(content);
                }
                dismiss();
            }
        });
    }

    public AddCardDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

}
