package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView mTextViewResult;
    private TextView date_text;
    public static JSONObject json;
    public String date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner dropdown = findViewById(R.id.spinner);
        ImageButton get_predictions =  findViewById(R.id.get_prediction);
        Button item_predict = findViewById(R.id.item_pred);
        final Button date = findViewById(R.id.date);
        date_text = findViewById(R.id.date_text);
        mTextViewResult = findViewById(R.id.textView);

        final OkHttpClient client = new OkHttpClient();

        String url = "http://192.168.0.26:8000/prediction";

        final Request request = new Request.Builder()
                .url(url)
                .build();

        final String[] items = new String[]{"Item X", "Item Y", "Item Z" , "Item W"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                
            }
        });
        get_predictions.setOnClickListener(new View.OnClickListener() {
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

                            Main2Activity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String myResp1 = myResp.replace("[","");
                                    String myResp2 = myResp1.replace("]","");
                                    String myResp3 = myResp2.replaceAll("\"","");
                                    String[] words = myResp3.split(",");
                                    items[0] = words[0].trim();
                                    items[1] = words[1].trim();
                                    items[2] = words[2].trim();
                                    items[3] = words[3].trim();
                                }
                            });
                        }
                    }
                });
            }
        });



        item_predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);     
                String text1 = spinner.getSelectedItem().toString();
                //Toast.makeText(Main2Activity.this, text1, Toast.LENGTH_SHORT).show();
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("item", text1);
                builder.add("date",date1);
                RequestBody formBody = builder
                        .build();
                  Request request1 = new Request.Builder()
                          .url("http://192.168.0.26:8000/prediction")
                          .post(formBody)
                          .build();
                  client.newCall(request1).enqueue(new Callback() {
                      @Override
                      public void onFailure(Call call, IOException e) {
                           e.printStackTrace();
                      }

                      @Override
                      public void onResponse(Call call, Response response) throws IOException {
                          if (response.isSuccessful()){
                               final String pred = response.body().string();
                               Main2Activity.this.runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       mTextViewResult.setText(pred);
                                   }
                               });

                          }
                      }
                  });
            }
        });

    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
          date1 = year + "/" + month + "/" + dayOfMonth;
          date_text.setText(date1);
    }
}
