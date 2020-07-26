package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static final String HELP = "1. Here You Enter Latitude and longitude of a city in Latitude Textbox and Longitude Textbox\n\n" +
            "2. Then Simply click to the FIND NEARBY CITIES and you will see the all cities that are nearest to that city whose latitude and longitude are entered\n\n" +
            "3. FAVOURITE CITIES button will show you the list of favourite cities\n\n" +
            "4. When you click on FIND NEARBY CITIES button the latitude and longitude will save in device using SHARED PREFERENCES\n\n" +
            "5. Shared preferences allow you to store small amounts of primitive data as key/value pairs in a file on the device\n\n\n" +
            "\n\n";


    public static AppDatabase appDatabase;
    TextInputEditText edtLat;
    TextInputEditText edtLng;

    MaterialButton btnFindNearbyCities;
    MaterialButton btnFavCities;

    double lat, lng;
    double lati;
    double longi;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String latitude = "latitude";
    public static final String longitude = "longitude";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Nearest Cities");

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "FavCitiesDb").fallbackToDestructiveMigration().allowMainThreadQueries().build();


        btnFindNearbyCities = findViewById(R.id.btn_find_cities);
        btnFavCities = findViewById(R.id.btn_show_fav_cities);
        edtLat = findViewById(R.id.edt_lat);
        edtLng = findViewById(R.id.edt_lng);

        btnFavCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavCitiesActivity.class);
                startActivity(intent);
            }
        });


        btnFindNearbyCities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    lat = Double.parseDouble(edtLat.getText().toString());
                    lng = Double.parseDouble(edtLng.getText().toString());


                saveData();

                Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);

                startActivity(intent);


            }
        });


        loadData();
        updateViews();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.help) {
            Common.showHelp(MainActivity.this, HELP);
            return true;
        } else {
            return super.onOptionsItemSelected(item);

        }

    }



    private void updateViews() {

        edtLat.setText(String.valueOf(lati));
        edtLng.setText(String.valueOf(longi));
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        lati = Double.parseDouble(sharedPreferences.getString(latitude, "0.00"));
        longi = Double.parseDouble(sharedPreferences.getString(longitude, "0.00"));
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(latitude, String.valueOf(lat));
        editor.putString(longitude, String.valueOf(lng));

        editor.apply();

        Toast.makeText(this, "Latitude and logitude Saved", Toast.LENGTH_SHORT).show();

    }
}