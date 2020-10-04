package com.omarlet.trelloapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.omarlet.trelloapp.R;
import com.omarlet.trelloapp.adapter.BoardRecyclerView;
import com.omarlet.trelloapp.model.Board;
import com.omarlet.trelloapp.model.BoardClick;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BoardClick {

    public static final String API_KEY = "API";
    //TODO: Trello log in, currently using a test token
    public static String TOKEN = "TOKEN";

    private ArrayList<Board> boards = new ArrayList<>();
    private RecyclerView boardRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boardRV = findViewById(R.id.boards);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        boardRV.setLayoutManager(lm);

        requestInformation();
    }

    private void requestInformation() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "https://api.trello.com/1/members/me/boards?key=" + API_KEY + "&token=" + TOKEN, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                JSONObject prefs = jsonObject.getJSONObject("prefs");
                                String name = jsonObject.getString("name");
                                String desc = jsonObject.getString("desc");
                                String id = jsonObject.getString("id");

                                String picture = prefs.getString("backgroundImage");
                                boards.add(new Board(name,desc,id,picture));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        setupBoards();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error retrieving info",Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);
    }

    private void setupBoards(){
        boardRV.setAdapter(new BoardRecyclerView(this, boards,this));
    }

    @Override
    public void OnBoardClick(int pos) {
        Intent intent = new Intent(this, BoardActivity.class);
        intent.putExtra("Board",boards.get(pos));
        startActivity(intent);
    }
}