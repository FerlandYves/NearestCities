package com.example.nearestcities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_city")
public class FavCityEntity {

    @PrimaryKey
    @NonNull
    private String city;

    @Ignore
    public FavCityEntity(@NonNull String city) {

        this.city = city;
    }

    public FavCityEntity() {

    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }
}
