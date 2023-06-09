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
import com.example.h071211050_finalmobile.MovieResponse;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private final String imgBaseUrl = "https://image.tmdb.org/t/p/w500";
    private List<MovieResponse> dataMovie;

    public MovieAdapter(List<MovieResponse> dataMovie) {
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
        MovieResponse movieResponse = dataMovie.get(position);
        holder.title.setText(movieResponse.getTitle());
        holder.release_date.setText(movieResponse.getReleaseDate().substring(0, 4));
        Glide.with(holder.itemView.getContext())
                .load(imgBaseUrl + movieResponse.getPosterPath())
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(holder.poster);
        holder.item_grid.setOnClickListener(v -> {
            Intent i = new Intent(holder.itemView.getContext(), DetailMovieTvActivity.class);
            i.putExtra(DetailMovieTvActivity.EXTRA_ITEM, movieResponse);
            i.putExtra(DetailMovieTvActivity.EXTRA_TYPE, DetailMovieTvActivity.TYPE_MOVIE);
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return dataMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title, release_date;
        CardView item_grid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            title = itemView.findViewById(R.id.title_tv);
            release_date= itemView.findViewById(R.id.release_year_tv);
            item_grid = itemView.findViewById(R.id.item_grid);
        }

    }
}
