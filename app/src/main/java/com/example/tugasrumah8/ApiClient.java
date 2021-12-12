package com.example.tugasrumah8;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private final static String API_KEY =
            "fac52f1464d962811ecc9d1076d21606";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static Retrofit retrofit;

    public static String getApiKey() {
        return API_KEY;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

