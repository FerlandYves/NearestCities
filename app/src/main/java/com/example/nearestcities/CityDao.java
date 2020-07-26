package com.example.nearestcities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FavouriteCities cityName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FavCityEntity cityName);

    @Query("select city from fav_city")
    public List<String> getCitiesNames();

    @Query("SELECT * FROM favourite_cities_table where city = :cityName")
    public City getCityDetails(String cityName);



    @Delete
    public void delete(FavouriteCities city);

    @Delete
    public void delete(FavCityEntity city);


}
