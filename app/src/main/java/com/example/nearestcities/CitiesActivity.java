package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CitiesActivity extends AppCompatActivity {
    private static final String TAG = "CitiesActivity";

    public static final String HELP = "1. Here you will see the all cities that are nearest to that city whose latitude and longitude are entered in Nearest Cities screen\n\n" +
            "2. On click to the CITY NAME and you will redirected to City Details Screen\n\n" +
            "3. On City Details Screen you will see all details about City you Clicked\n\n" +
            "4. On click to the favourite button that city will be added as favourite city in database and a TOAST will appear\n\n" +
            "";
    public static final String BASE_URL = "https://api.geodatasource.com/";

    public static final String API_KEY1 = "ONUUQGC3HGKV8A8ZPBQEQLPFEQD77ECA";


    ListAdapter adapter;


    ListView listViewCities;
    List<String> citiesList;
    List<City> cityList;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        setTitle("Nearby Cities");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        progressBar = findViewById(R.id.bar);

        listViewCities = findViewById(R.id.lv_cities);
        citiesList = new ArrayList<>();
        cityList = new ArrayList<>();


        double lat = getIntent().getDoubleExtra("lat", 0.00);
        double lng = getIntent().getDoubleExtra("lng", 0.00);

        Retrofit retrofit = RetrofitClient.getRetrofit(BASE_URL);
        GeoDataApi geoDataApi = retrofit.create(GeoDataApi.class);

        Call<List<City>> nearbyCities = geoDataApi.getNearbyCities(API_KEY1, "json", lat, lng);


        progressBar.setVisibility(View.VISIBLE);
        nearbyCities.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {


                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);
                    List<City> cities = response.body();
                    for (City aCity : cities) {
                        citiesList.add(aCity.getCity());
                        cityList.add(aCity);


                    }

                }


                adapter = new ListAdapter(getApplicationContext(), R.layout.activity_cities, (ArrayList<City>) cityList);
                listViewCities.setAdapter(adapter);

                adapter.notifyDataSetChanged();

                listViewCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        long itemIdAtPosition = parent.getItemIdAtPosition(position);

                        Intent intent = new Intent(CitiesActivity.this, DetailActivity.class);
                        intent.putExtra("cityList", cityList.get((int) itemIdAtPosition));

                        startActivity(intent);


                    }
                });
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {


                Toast.makeText(CitiesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }


        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.help) {
            Common.showHelp(CitiesActivity.this, HELP);
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