package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        final Spinner dropdown = findViewById(R.id.item_list);
        final Spinner quantity = findViewById(R.id.quantity);
        Button place_order = findViewById(R.id.place_order);
        final OkHttpClient client = new OkHttpClient();
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.finalproject", Context.MODE_PRIVATE);

        final String[] items = new String[]{"Item 1", "Item 2", "Item 3" , "Item 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        final String[] Quantity = new String[]{"1", "2", "3" , "4"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Quantity);
        quantity.setAdapter(adapter1);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = dropdown.getSelectedItem().toString();
                String s = quantity.getSelectedItem().toString();
                int quan = Integer.parseInt(s);
                String product_price = Integer.toString(quan*60);
                String customer_name = sharedPreferences.getString("customer_name","");
                String customer_id = sharedPreferences.getString("customer_id","");
                String restaurant_id = sharedPreferences.getString("restaurant_id","");
                String center_id = sharedPreferences.getString("center_id","");
                String location = "Kormangala";
                if(center_id == "102"){
                    location = "Banashankri";
                }
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("customer_name",customer_name);
                builder.add("customer_id",customer_id);
                builder.add("restaurant_id",restaurant_id);
                builder.add("center_id",center_id);
                builder.add("item_name", item);
                builder.add("location",location);
                builder.add("product_price",product_price);
                builder.add("latitude","77.77");
                builder.add("longitude","12.22");
                RequestBody formBody = builder
                        .build();
                Request request1 = new Request.Builder()
                        .url("http://192.168.0.26:8000/orders")
                        .post(formBody)
                        .build();
                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if(response.isSuccessful()){
                            final String pred = response.body().string();
                            Main7Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Main7Activity.this, pred, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                Intent i = new Intent(Main7Activity.this, Main8Activity.class);
                startActivity(i);
            }
        });
    }
}
