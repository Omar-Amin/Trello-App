package com.omarlet.trelloapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.omarlet.trelloapp.R;
import com.omarlet.trelloapp.activity.MainActivity;
import com.omarlet.trelloapp.model.Card;
import com.omarlet.trelloapp.model.List;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCardDialog extends Dialog {

    private EditText cardContent;
    private Context context;
    private List list;
    private Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_layout);

        final RequestQueue queue = Volley.newRequestQueue(context);

        Button addCard = findViewById(R.id.addNewCard);
        cardContent = findViewById(R.id.addCardContent);

        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Add post request to the card and update the list
                final String content = cardContent.getText().toString();
                if (!content.isEmpty()){
                    //Post to trello
                    JSONObject newCard = new JSONObject();

                    try {
                        newCard.put("name",content);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    queue.add(new JsonObjectRequest(Request.Method.POST, "https://api.trello.com/1/cards?key=" + MainActivity.API_KEY + "&token=" + MainActivity.TOKEN + "&idList=" + list.getId(),
                            newCard, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                card = new Card(list.getId(),response.getString("dateLastActivity"),content);
                            } catch (JSONException ignored) { }
                            dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }));
                }else {
                    dismiss();
                }
            }
        });
    }

    public AddCardDialog(@NonNull Context context, List list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    public Card getCard() {
        return card;
    }
}
