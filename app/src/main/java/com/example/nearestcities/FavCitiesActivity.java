package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FavCitiesActivity extends AppCompatActivity {

    public static final String HELP = "1. Here You will see a list of Favourite cities you added in Nearby Cities Screen\n\n" +
            "2. On click on DELETE button that city will removed from database and a TOAST will appear\n\n" +
            "3. On click on City name you will see  details of favourite cities\n\n";


    ListView listViewFavCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_cities);
        setTitle("Favourite Cities");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewFavCities = findViewById(R.id.lv_fav_cities);

        List<String> citiesNames = MainActivity.appDatabase.dao().getCitiesNames();


        FavCitiesListAdapter adapter = new FavCitiesListAdapter(getApplicationContext(), R.layout.activity_fav_cities, (ArrayList<String>) citiesNames);
        listViewFavCities.setAdapter(adapter);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.help) {

            Common.showHelp(FavCitiesActivity.this,HELP);
            return true;
        }else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else{

        return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.help,menu);
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}