package com.example.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.target.Target;
import com.example.flixter.MainActivity;
import com.example.flixter.MovieDetailsActivity;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from an XML file and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(MainActivity.TAG, "onCreateViewHolder executes");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through the viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get movie at position
        Movie movie = movies.get(position);
        // Bind movie data into viewholder
        holder.bind(movie);
        
    }

    // Returns total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            itemView.setOnClickListener(this);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;
            // if phone is in portrait mode use poster
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackdropPath();
            }
            else{
                // If phone is in landscape use banner
                imageURL = movie.getPosterPath();
            }
            int radius = 30;
            int margin = 5;

            Glide.with(context).load(imageURL).transform(new RoundedCornersTransformation(radius, margin)).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(ivPoster);

        }

        @Override
        public void onClick(View view) {
            // Get the position & ensure it’s valid
            int position = getAdapterPosition();
            // Get the Movie at that position in the list
            Movie movie = movies.get(position);
            // Create an Intent to display MovieDetailsActivity
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            // Pass the movie as an extra serialized via Parcels.wrap()
            intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
            // Show the activity
            context.startActivity(intent);
        }
    }

}
