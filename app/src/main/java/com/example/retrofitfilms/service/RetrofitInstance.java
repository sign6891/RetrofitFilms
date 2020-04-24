package com.example.retrofitfilms.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit mRetrofit = null;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static JSONFilmsHolderApi getInstance() {

        if (mRetrofit == null) {

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit.create(JSONFilmsHolderApi.class);
    }
}
