package com.example.retrofitfilms.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitfilms.InfoMovieActivity;
import com.example.retrofitfilms.MainActivity;
import com.example.retrofitfilms.R;
import com.example.retrofitfilms.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Context context;
    private ArrayList<Result> resultArrayList;

    public MovieAdapter(Context context, ArrayList<Result> resultList) {
        this.context = context;
        this.resultArrayList = resultList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);

        return new MovieViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.nameFilmsTextView.setText(resultArrayList.get(position).getOriginalTitle());
        holder.countPopularityTextView.setText(resultArrayList.get(position).getPopularity().toString());
        String image = "https://image.tmdb.org/t/p/w500/" + resultArrayList.get(position).getPosterPath();

        Glide.with(context)
                .load(image)
                //.placeholder(R.drawable.original)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView nameFilmsTextView;
        TextView countPopularityTextView;
        ImageView imageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameFilmsTextView = itemView.findViewById(R.id.nameFilmsTextView);
            countPopularityTextView = itemView.findViewById(R.id.countPopularityTextView);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {

                        Result result = resultArrayList.get(position);
                        Intent intent = new Intent(context, InfoMovieActivity.class);
                        intent.putExtra("movieData", result);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
