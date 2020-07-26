package com.example.nearestcities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoDataApi {

    @GET("cities")
    Call<List<City>> getNearbyCities(
            @Query("key") String key,
            @Query("format") String format,
            @Query("lat") double lat,
            @Query("lng") double lng


    );
}
