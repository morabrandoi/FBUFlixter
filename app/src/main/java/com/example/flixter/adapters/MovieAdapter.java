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
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through the view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind movie data into view holder
        Movie movie = movies.get(position);
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
            // Filling Views

            // Title Text
            tvTitle.setText(movie.getTitle());

            // Overview Text
            String overview = movie.getOverview();
            String truncated = overview.length() < 300 ? overview: overview.substring(0,300) + "...";
            tvOverview.setText(truncated);

            // Image View
            String imageURL;
            int backupImage;
            // if phone is in portrait mode use poster
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageURL = movie.getBackdropPath();
                backupImage = R.drawable.flicks_backdrop_placeholder;
            }
            else{
                // If phone is in landscape use banner
                imageURL = movie.getPosterPath();
                backupImage = R.drawable.flicks_movie_placeholder;
            }
            int radius = 30;
            int margin = 5;
            Glide.with(context).load(imageURL).placeholder(backupImage).transform(new RoundedCornersTransformation(radius, margin)).into(ivPoster);
        }

        @Override
        public void onClick(View view) {
            // Get the Movie at that position in the list
            int position = getAdapterPosition();
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
