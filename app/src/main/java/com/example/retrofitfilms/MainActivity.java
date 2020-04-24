package com.example.retrofitfilms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.retrofitfilms.adapter.FilmsAdapter;
import com.example.retrofitfilms.databinding.ActivityMainBinding;
import com.example.retrofitfilms.model.Result;
import com.example.retrofitfilms.viewmodel.MainActivityViewModel;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FilmsAdapter filmsAdapter;
    ArrayList<Result> resultArrayList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);
        getFilms();

        //Свайп сверху вниз для обновления страницы
        swipeRefreshLayout = activityMainBinding.swiperefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFilms();
            }
        });
    }

    private void getFilms() {

       mainActivityViewModel.getAllFilmsData().observe(this, new Observer<List<Result>>() {
           @Override
           public void onChanged(List<Result> results) {
               resultArrayList = (ArrayList<Result>) results;
               fillRecyclerView();
           }
       });
    }

    private void fillRecyclerView() {

        recyclerView = activityMainBinding.recyclerView;

        filmsAdapter = new FilmsAdapter(this, resultArrayList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Загружаем инфу в макет с GridLayout. Второе значение означает количество столбцов
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(filmsAdapter);
        filmsAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
