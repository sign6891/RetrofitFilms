package com.example.retrofitfilms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.retrofitfilms.adapter.MovieAdapter;
import com.example.retrofitfilms.model.Example;
import com.example.retrofitfilms.model.Result;
import com.example.retrofitfilms.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MovieAdapter movieAdapter;
    ArrayList<Result> resultArrayList;
    public static final String API_KEY = "a74f7a9542fbed2b84a773bd6af13cfc";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMovie();

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovie();
            }
        });
    }

    private Object getMovie() {
        RetrofitInstance.getInstance()
                .getPopularMovies(API_KEY)
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {

                        Example example = response.body();

                        if (example != null && example.getResults() != null) {
                            resultArrayList = (ArrayList<Result>) example.getResults();
                            fillRecyclerView();
                            swipeRefreshLayout.setRefreshing(false);

                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return resultArrayList;
    }

    private void fillRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView);

        movieAdapter = new MovieAdapter(this, resultArrayList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Загружаем инфу в макет с GridLayout. Второе значение означает количество столбцов
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}
