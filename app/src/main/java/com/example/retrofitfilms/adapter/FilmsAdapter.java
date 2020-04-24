package com.example.retrofitfilms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitfilms.InfoFilmsActivity;
import com.example.retrofitfilms.R;
import com.example.retrofitfilms.databinding.MovieItemBinding;
import com.example.retrofitfilms.model.Result;


import java.util.ArrayList;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder> {

    Context context;
    private ArrayList<Result> resultArrayList;

    public FilmsAdapter(Context context, ArrayList<Result> resultList) {
        this.context = context;
        this.resultArrayList = resultList;
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent,false);

        return new FilmsViewHolder(movieItemBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, int position) {

        Result result = resultArrayList.get(position);

        holder.movieItemBinding.setResult(result);

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public class FilmsViewHolder extends RecyclerView.ViewHolder {

        private MovieItemBinding movieItemBinding;

        public FilmsViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;


            movieItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Result result = resultArrayList.get(position);
                        Intent intent = new Intent(context, InfoFilmsActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
