package com.omarlet.trelloapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omarlet.trelloapp.R;
import com.omarlet.trelloapp.adapter.ListRecyclerView;
import com.omarlet.trelloapp.model.Board;
import com.omarlet.trelloapp.model.Card;
import com.omarlet.trelloapp.model.CardAdded;
import com.omarlet.trelloapp.model.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BoardActivity extends AppCompatActivity implements CardAdded {

    private HashMap<String,List> listsHm = new HashMap<>();
    private ArrayList<List> lists = new ArrayList<>();
    private RecyclerView listRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        listRV = findViewById(R.id.boardContent);
        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        listRV.setLayoutManager(lm);

        getCards();
    }

    // GET request: Retrieves all cards from the selected board
    private void getCards() {
        Board board = (Board) getIntent().getSerializableExtra("Board");

        final RequestQueue queue = Volley.newRequestQueue(this);

        assert board != null;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "https://api.trello.com/1/boards/" + board.getId() + "/cards?key=" + MainActivity.API_KEY + "&token=" + MainActivity.TOKEN, null,
                new Response.Listener<JSONArray>() {

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String listId = jsonObject.getString("idList");
                                String date = jsonObject.getString("dateLastActivity");
                                String name = jsonObject.getString("name");

                                List list = listsHm.get(listId);
                                if(list == null){
                                    list = new List(listId);
                                    listsHm.put(listId,list);
                                    queue.add(getListInfo(listId));
                                }

                                Card card = new Card(listId,date,name);
                                Objects.requireNonNull(listsHm.get(listId)).addCard(card);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (HashMap.Entry<String, List> obj : listsHm.entrySet()) {
                            List value = obj.getValue();
                            lists.add(value);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BoardActivity.this,"Error retrieving info",Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);
    }

    // retrieves information about the list and sets the name of the list
    private JsonObjectRequest getListInfo(final String id){
        return new JsonObjectRequest(Request.Method.GET, "https://api.trello.com/1/lists/" + id + "?key=" + MainActivity.API_KEY + "&token=" + MainActivity.TOKEN,null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Objects.requireNonNull(listsHm.get(id)).setName(response.getString("name"));
                            setupLists();
                        } catch (JSONException ignored) { }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BoardActivity.this, "Error retrieving list infor",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setupLists(){
        listRV.setAdapter(new ListRecyclerView(this, lists,this));
    }

    @Override
    public void refresh(Card card, int pos) {
        lists.get(pos).addCard(card);
        setupLists();
    }
}