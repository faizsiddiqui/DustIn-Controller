package com.major.dustincontroller.utils;

import com.major.dustincontroller.model.Level;
import com.major.dustincontroller.model.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Faiz on 20-05-2016.
 */
public interface API {

    @GET("dustin/location")
    Call<Location> getLocation();

    @GET("dustin/level")
    Call<Level> getLevel();

}
