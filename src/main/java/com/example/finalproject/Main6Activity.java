package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Button faasos = findViewById(R.id.faasos);
        Button dominos = findViewById(R.id.dominos);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.finalproject", Context.MODE_PRIVATE);

        faasos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("restaurant_id","11").apply();
                sharedPreferences.edit().putString("center_id","101").apply();
                Intent i = new Intent(Main6Activity.this,Main7Activity.class);
                startActivity(i);
            }
        });
    }
}
