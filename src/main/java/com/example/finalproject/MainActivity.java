package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    public static JSONObject json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getRequest = findViewById(R.id.getRequest);
        Button center1 = findViewById(R.id.button);
        Button center2 = findViewById(R.id.button2);
        Button center3 = findViewById(R.id.button3);
        mTextViewResult = findViewById(R.id.textView2);
        Button prediction = (Button) findViewById(R.id.predict);
        Button customer_login = findViewById(R.id.customer_login);


        final OkHttpClient client = new OkHttpClient();

        String url = "http://192.168.0.26:8000/clusterdata";

        final Request request = new Request.Builder()
                .url(url)
                .build();


        getRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            final String myResp = response.body().string();
                            try {
                                json = new JSONObject(myResp);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTextViewResult.setText(myResp);
                                }
                            });
                        }
                    }
                });
            }
        });
        center1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String first_lat = null,first_lon = null;
                try {
                    first_lat = (String) json.get("first_lat");
                    first_lon = (String) json.get("first_lon");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+first_lat+","+first_lon);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        center2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String second_lat = null,second_lon = null;
                try {
                    second_lat = (String) json.get("second_lat");
                    second_lon = (String) json.get("second_lon");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+second_lat+","+second_lon);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        center3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String third_lat = null,third_lon = null;
                try {
                    third_lat = (String) json.get("third_lat");
                    third_lon = (String) json.get("third_lon");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+third_lat+","+third_lon);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        prediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(i);
            }
        });
        customer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main5Activity.class);
                startActivity(i);
            }
        });

    }
}
