package com.example.retrofitfilms.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitfilms.R;
import com.example.retrofitfilms.service.JSONFilmsHolderApi;
import com.example.retrofitfilms.service.RetrofitInstance;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsRepository {

    //public static final String API_KEY = "a74f7a9542fbed2b84a773bd6af13cfc";
    private ArrayList<Result> resultArrayList = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public FilmsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {

        JSONFilmsHolderApi jsonFilmsHolderApi =  RetrofitInstance.getInstance();
        Call<Example> call = jsonFilmsHolderApi.getPopularMovies(
                application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();

                if (example != null && example.getResults() != null) {
                    resultArrayList = (ArrayList<Result>) example.getResults();
                    mutableLiveData.setValue(resultArrayList);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
