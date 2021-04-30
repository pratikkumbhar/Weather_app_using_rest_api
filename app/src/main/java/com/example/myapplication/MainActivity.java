package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
Button getcityid,getwetherbyid,getweatherbyname;
ListView listview;
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editext);
        listview = findViewById(R.id.listview);
        getcityid = findViewById(R.id.getcityid);
        getweatherbyname = findViewById(R.id.weatherbyname);
        getwetherbyid = findViewById(R.id.weatherbyid);
       final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
        getcityid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            weatherDataService.getCityId(editText.getText().toString(), new WeatherDataService.VolleyResponseListner() {
                @Override
                public void onError(String message) {

                }

                @Override
                public void onResponse(String cityId) {
                    Toast.makeText(MainActivity.this, "MainActivity"+ cityId, Toast.LENGTH_SHORT).show();
                }
            });

            }
        });

        getweatherbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForcastbyName(editText.getText().toString(), new WeatherDataService.getCityForcastbyNameCallback() {


                    @Override
                    public void OnError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> model) {

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,model);
                        listview.setAdapter(arrayAdapter);
                    }
                });


            }
        });

        getwetherbyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    weatherDataService.getCityForecasetByID(editText.getText().toString(), new WeatherDataService.ForcastById() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(List<WeatherReportModel> model) {

                            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,model);
                            listview.setAdapter(arrayAdapter);
                        }
                    });
            }
        });

    }
}