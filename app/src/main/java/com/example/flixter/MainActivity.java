package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.adapters.MovieAdapter;
import com.example.flixter.databinding.ActivityMainBinding;
import com.example.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
// STATIC
    // public final static String API_KEY = getString(R.string.movie_api_key);
    public static final String API_KEY = "610823cd7256a3bec2e05f09f2c8ad28";
    public static final String NOW_PLAYING_URL= "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY;
    public static final String TAG = "MainActivity";

// Non-Static
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Utilizing View binding
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initializing reference in movies. Will be passed to adapter
        movies = new ArrayList<>();

        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        // Giving rvMovies necessary adapter and layout manager
        binding.rvMovies.setAdapter(movieAdapter);
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this));

        // Making Get Request and parsing it into movies variable.
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "successful API GET");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");

                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "Hit Json Exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "Failure to access API");
            }
        });
    }
}