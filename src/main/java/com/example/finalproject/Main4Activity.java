package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Switch korman = findViewById(R.id.koram_switch);
        Switch banash = findViewById(R.id.bana_switch);
        Switch white = findViewById(R.id.white_switch);
        Button save = findViewById(R.id.save);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("192.168.0.105:8000/serivce")
                .build();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Boolean korman_state =
            }
        });


    }
}
