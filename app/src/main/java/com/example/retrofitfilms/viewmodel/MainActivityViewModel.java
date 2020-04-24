package com.example.retrofitfilms.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofitfilms.model.FilmsRepository;
import com.example.retrofitfilms.model.Result;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private FilmsRepository filmsRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        filmsRepository = new FilmsRepository(application);
    }

    public LiveData<List<Result>> getAllFilmsData(){


        return filmsRepository.getMutableLiveData();
    }
}
