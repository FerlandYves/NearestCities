package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class FavCitiesDetailActivity extends AppCompatActivity {
    private static final String TAG = "FavCitiesDetailActivity";

    public static final String HELP = "1. Here You will see details of favourite cities\n\n" +
            "2. Details include Country,Region,City,Currency,Latitude and Longitude\n\n";


    TextView txtCountry, txtRegion, txtCity, txtCurrency, txtLatitude, txtLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_cities_detail);
        setTitle("Favourite Cities Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtCountry = findViewById(R.id.txt_country_name);
        txtRegion = findViewById(R.id.txt_region_name);
        txtCity = findViewById(R.id.txt_city_name);
        txtCurrency = findViewById(R.id.txt_currency_name);
        txtLatitude = findViewById(R.id.txt_lat);
        txtLongitude = findViewById(R.id.txt_lng);


        String city = getIntent().getStringExtra("cityName");

        City cityDetails = MainActivity.appDatabase.dao().getCityDetails(city);


        txtCountry.setText(cityDetails.getCountry());
        txtRegion.setText(cityDetails.getRegion());
        txtCity.setText(cityDetails.getCity());
        txtCurrency.setText(cityDetails.getCurrency_name());
        txtLatitude.setText(cityDetails.getLatitude());
        txtLongitude.setText(cityDetails.getLongitude());


        Log.d(TAG, "onCreate: called");


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.help) {
            Common.showHelp(FavCitiesDetailActivity.this, HELP);
            return true;
        }else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }  else {

            return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}