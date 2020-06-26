package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.databinding.ActivityMovieDetailsBinding;
import com.example.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import okhttp3.Headers;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movie;
    String trailer_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // pulling movie object out of parcel
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        assert movie != null;

        // G
        // Making Get Request and parsing it into trailer_key variable.
        AsyncHttpClient client = new AsyncHttpClient();
        String movie_endpoint = "https://api.themoviedb.org/3/movie/" + movie.getId() + "/videos?api_key=" + getString(R.string.movie_api_key);
        Log.d("XYZ", "FULL ENDPOINT REQUEST: " + movie_endpoint);
        client.get(movie_endpoint, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d("XYZ", "successful API GET");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONObject results = jsonObject.getJSONArray("results").getJSONObject(0);
                    // Do stuff with results
                    trailer_key = results.getString("key");
                    Log.i("XYZ", "TRAILER KEY: " + trailer_key );
                } catch (JSONException e) {
                    Log.e("XYZ", "Hit Json Exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d("XYZ", "Failure to access API");
            }
        });

        // Using ViewBinding to facilitate getting activity views
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Filling View Objects with data
        assert movie != null;
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

        // Creating onClick Listener on Image
        binding.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create an Intent to display MovieDetailsActivity
                Context context = getApplicationContext();
                Intent intent = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                // Pass the movie as an extra serialized via Parcels.wrap()
                intent.putExtra(MovieDetailsActivity.class.getSimpleName(), trailer_key);
                // Show the activity
                MovieDetailsActivity.this.startActivity(intent);
            }
        });

    }
}