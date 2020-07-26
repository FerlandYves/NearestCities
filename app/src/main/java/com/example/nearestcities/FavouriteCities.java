package com.example.nearestcities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_cities_table",primaryKeys = {"city"})
public class FavouriteCities {



    @NonNull
    private String city;
    private String country;
    private String region;
    private String currency_name;
    private String latitude;
    private String longitude;


    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public FavouriteCities(@NonNull String city, String country, String region, String currency_name, String latitude, String longitude) {
        this.city = city;
        this.country = country;
        this.region = region;
        this.currency_name = currency_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
