package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String HELP = "1. Here Google maps comes in a play \n\n" +
            "2. You will see a marker on a city location\n\n" +
            "3. You can zoom in and out\n\n" +
            "";

    GoogleMap mMap;

    double latitude;
    double longitude;
    String cityName;
    private static final String TAG = "MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setTitle("Map");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        supportMapFragment.getMapAsync(this);

        City city = (City) getIntent().getSerializableExtra("city");
        latitude = Double.parseDouble(city.getLatitude());
        longitude = Double.parseDouble(city.getLongitude());
        cityName = city.getCity();

        supportMapFragment.getMapAsync(this);

        Log.d(TAG, "onCreate: called");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title(cityName)).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.help) {

            Common.showHelp(MapsActivity.this, HELP);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {

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