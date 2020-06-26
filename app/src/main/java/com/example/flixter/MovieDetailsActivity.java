package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

//    Set the title and overview from the movie
//    Set the RatingBar value by diving Movie.getVoteAverage by 2.0

    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ivPoster;


    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Pulling in views in XML file
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        ivPoster = findViewById(R.id.ivPoster);

        // pulling movie object of view
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

    // FILLING VIEW OBJECTS WITH DATA
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // Filling ratings bar
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);

        // Filling imageView
        int radius = 30;
        int margin = 10;

        String imageURL;
        // If phone is in portrait use portrait otherwise use banner
        if ((getApplicationContext()).getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageURL = movie.getBackdropPath();
        }
        else{
            imageURL = movie.getPosterPath();
        }

        Glide.with(getApplicationContext()).load(imageURL).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);

    }
}