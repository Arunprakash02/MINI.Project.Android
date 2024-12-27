package com.abc.projexct_customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIclient {

    private static APIclient apiClient;
    private Retrofit retrofit;  // Make retrofit non-static to avoid initialization issues
    private static final String BASE_URL = "http://192.168.1.37:8010/";

    // Private constructor for Singleton
    private APIclient() {
        // Initialize Retrofit instance
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    // Singleton instance getter
    public static synchronized APIclient getapiClient() {
        if (apiClient == null) {
            apiClient = new APIclient();
        }
        return apiClient;
    }

    // Provide API interface
    public APIinterface getapiInterface() {
        if (retrofit == null) {
            throw new IllegalStateException("Retrofit instance is not initialized");
        }
        return retrofit.create(APIinterface.class);
    }
}
