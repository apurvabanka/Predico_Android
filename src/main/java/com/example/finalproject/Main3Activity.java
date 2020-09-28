package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button login = findViewById(R.id.login);
        Spinner dropdown = findViewById(R.id.spinner2);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin")){
                    Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                    startActivity(i);
                }
                if((username.getText().toString().equals("a")) && password.getText().toString().equals("a")) {
                    Intent i = new Intent(Main3Activity.this, Main2Activity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Main3Activity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final String[] items = new String[]{"Kormangala", "WhiteField", "Banashankri" , "HSR Layout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }
}
