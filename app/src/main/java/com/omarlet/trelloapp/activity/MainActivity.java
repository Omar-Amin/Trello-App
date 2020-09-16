package com.omarlet.trelloapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.omarlet.trelloapp.R;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "API_KEY";
    //TODO: Trello log in, currently using a test token
    private String TOKEN = "TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new UserInformation().execute();
    }

    private class UserInformation extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/me/boards?key=" + API_KEY + "&token=" + TOKEN).asJson();
            System.out.println(response.getBody().getArray().get(3).toString());
            return null;
        }
    }
}