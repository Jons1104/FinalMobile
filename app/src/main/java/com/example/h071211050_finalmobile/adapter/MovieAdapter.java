package com.example.h071211050_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211050_finalmobile.DetailMovieTvActivity;
import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.models.FavoriteModel;
import com.example.h071211050_finalmobile.models.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private List<MovieModel> dataMovie;

    public MovieAdapter(List<MovieModel> dataMovie) {
        this.dataMovie = dataMovie;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movieModel = dataMovie.get(position);

        if (movieModel.getTitle().equals("") || movieModel.getTitle() == null)
            holder.title_movie.setText("-");
        else
            holder.title_movie.setText(movieModel.getTitle());

        if (!movieModel.getReleaseDate().equals("") || movieModel.getReleaseDate() != null)
            holder.release_year.setText(movieModel.getReleaseDate().substring(0, 4));
        else
            holder.release_year.setText("-");

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + movieModel.getPosterPath()).centerCrop().into(holder.Poster);
        holder.item_grid_cv.setOnClickListener(v -> {
            FavoriteModel favouriteModel = new FavoriteModel(
                    movieModel.getId(),
                    movieModel.getTitle(),
                    movieModel.getReleaseDate(),
                    movieModel.getOverview(),
                    movieModel.getPosterPath(),
                    movieModel.getBackdropPath(),
                    movieModel.getVoteAverage(),
                    DetailMovieTvActivity.TYPE_MOVIE );

            Intent i = new Intent(holder.itemView.getContext(), DetailMovieTvActivity.class);
            i.putExtra(DetailMovieTvActivity.EXTRA_ITEM, favouriteModel);
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return dataMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Poster;
        TextView title_movie, release_year;
        CardView item_grid_cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Poster = itemView.findViewById(R.id.poster);
            title_movie = itemView.findViewById(R.id.title2);
            release_year = itemView.findViewById(R.id.release_year2);
            item_grid_cv = itemView.findViewById(R.id.item_grid_cv);
        }

    }
}
