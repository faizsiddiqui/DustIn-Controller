package com.major.dustincontroller.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Faiz on 20-05-2016.
 */
public class APIClient {

    private static APIClient apiClient;
    private API api;

    private APIClient(String HOST) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(API.class);
    }

    public static APIClient getInstance(String HOST) {
        if (apiClient == null) {
            apiClient = new APIClient(HOST);
        }
        return apiClient;
    }

    public API getApi() {
        return api;
    }

}
