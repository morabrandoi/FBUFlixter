package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.databinding.ActivityMovieDetailsBinding;
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
        // setContentView(R.layout.activity_movie_details);

        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        // layout of activity is stored in a special property called root
        View view = binding.getRoot();
        setContentView(view);

        // pulling movie object of view
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

        // FILLING VIEW OBJECTS WITH DATA
        binding.tvTitle.setText(movie.getTitle());

        binding.tvOverview.setText(movie.getOverview());

        // Filling ratings bar
        float voteAverage = movie.getVoteAverage().floatValue();
        binding.rbVoteAverage.setRating(voteAverage / 2.0f);

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

        Glide.with(getApplicationContext()).load(imageURL).transform(new RoundedCornersTransformation(radius, margin)).into(binding.ivPoster);

    }
}