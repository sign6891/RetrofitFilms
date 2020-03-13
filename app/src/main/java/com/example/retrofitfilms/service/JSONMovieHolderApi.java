package com.example.retrofitfilms.service;

import com.example.retrofitfilms.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONMovieHolderApi {


//?api_key=a74f7a9542fbed2b84a773bd6af13cfc&language=en-US&page=1
    @GET("movie/popular")
    public Call<Example> getPopularMovies(@Query("api_key") String apiKey);
}
