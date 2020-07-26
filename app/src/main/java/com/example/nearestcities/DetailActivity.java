package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.io.Serializable;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    public static final String HELP = "1. Here you will see the all details about city that you clicked on Nearby Cities screen\n\n" +
            "2. On click to the GO TO MAP button you will redirected to google maps\n\n" +
            "3. After redirecting to GOOGLE MAPS you will see a marker showing the city location you clicked\n\n" +
            "";

    City city;
    TextView txtCountry, txtRegion, txtCity, txtCurrency, txtLatitude, txtLongitude;
    MaterialButton btnGoToMaps;


    //    Country, Region, City, Currency, Latitude, and Longitude.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnGoToMaps = findViewById(R.id.btn_go_to_maps);


        city = (City) getIntent().getSerializableExtra("cityList");


        txtCountry = findViewById(R.id.txt_country_name);
        txtRegion = findViewById(R.id.txt_region_name);
        txtCity = findViewById(R.id.txt_city_name);
        txtCurrency = findViewById(R.id.txt_currency_name);
        txtLatitude = findViewById(R.id.txt_lat);
        txtLongitude = findViewById(R.id.txt_lng);

        txtCountry.setText(city.getCountry());
        txtRegion.setText(city.getRegion());
        txtCity.setText(city.getCity());
        txtCurrency.setText(city.getCurrency_name());
        txtLatitude.setText(city.getLatitude());
        txtLongitude.setText(city.getLongitude());


        btnGoToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.help) {

            Common.showHelp(DetailActivity.this, HELP);
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