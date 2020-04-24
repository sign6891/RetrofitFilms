package com.example.retrofitfilms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.retrofitfilms.databinding.ActivityInfoMovieBinding;
import com.example.retrofitfilms.databinding.ActivityMainBinding;
import com.example.retrofitfilms.model.Result;


public class InfoFilmsActivity extends AppCompatActivity {

    private Result result;
    private ActivityInfoMovieBinding activityInfoMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_movie);

        activityInfoMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_info_movie);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movieData")) {

           result = intent.getParcelableExtra("movieData");

            activityInfoMovieBinding.setResult(result);

           /* if(result != null) {
                infoNameFilmsTextView.setText(result.getOriginalTitle());
                infoCountPopularityTextView.setText(result.getOverview());

                String image = "https://image.tmdb.org/t/p/w500/" + result.getPosterPath();

                Glide.with(this)
                        .load(image)
                        .placeholder(R.drawable.original)//при долгой загрузки видим изображение
                        .into(infoImageView);
            }*/
        }
    }
}
