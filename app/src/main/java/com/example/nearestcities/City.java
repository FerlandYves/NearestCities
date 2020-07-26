package com.example.nearestcities;

import java.io.Serializable;

public class City implements Serializable {


    private String country;
    private String region;
    private String city;
    private String latitude;
    private String longitude;
    private String currency_code;
    private String currency_name;
    private String currency_symbol;
    private String sunrise;
    private String sunset;
    private String time_zone;
    private String distance_km;

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public String getDistance_km() {
        return distance_km;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public void setDistance_km(String distance_km) {
        this.distance_km = distance_km;
    }
}
