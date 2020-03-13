package com.example.retrofitfilms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitfilms.model.Result;

public class InfoMovieActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movie);

        ImageView infoImageView = findViewById(R.id.infoImageView);
        TextView infoNameFilmsTextView = findViewById(R.id.infoNameFilmsTextView);
        TextView infoCountPopularityTextView = findViewById(R.id.infoCountPopularityTextView);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movieData")) {

           result = intent.getParcelableExtra("movieData");

            if(result != null) {
                infoNameFilmsTextView.setText(result.getOriginalTitle());
                infoCountPopularityTextView.setText(result.getOverview());

                String image = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();

                Glide.with(this)
                        .load(image)
                        .placeholder(R.drawable.original)//при долгой загрузки видим изображение
                        .into(infoImageView);
            }
        }
    }
}
