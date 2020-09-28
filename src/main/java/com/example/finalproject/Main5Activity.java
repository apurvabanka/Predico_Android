package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Button customer_login = findViewById(R.id.customer_login);
        final TextView customer_name = findViewById(R.id.customer_name);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.finalproject", Context.MODE_PRIVATE);


        customer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("customer_name",customer_name.getText().toString()).apply();
                sharedPreferences.edit().putString("customer_id","2").apply();
                Intent i = new Intent(Main5Activity.this,Main6Activity.class);
                startActivity(i);
            }
        });

    }
}
