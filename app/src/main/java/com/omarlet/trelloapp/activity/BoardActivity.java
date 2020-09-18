package com.omarlet.trelloapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.omarlet.trelloapp.R;
import com.omarlet.trelloapp.model.Board;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Board board = (Board) getIntent().getSerializableExtra("Board");
        assert board != null;
        System.out.println(board.toString());
    }
}