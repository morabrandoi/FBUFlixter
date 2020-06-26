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

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Using ViewBinding to facillitate getting activity views
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // pulling movie object out of parcel
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));

    // Filling View Objects with data
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());

        // Filling ratings bar
        float voteAverage = movie.getVoteAverage().floatValue();
        binding.rbVoteAverage.setRating(voteAverage / 2.0f);

        // Filling imageView
        int radius = 30;
        int margin = 10;
        String imageURL = movie.getBackdropPath();
        Glide.with(getApplicationContext()).load(imageURL).transform(new RoundedCornersTransformation(radius, margin)).into(binding.ivPoster);

    }
}