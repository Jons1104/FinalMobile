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
import com.example.h071211050_finalmobile.models.TvModel;

import java.util.List;
import java.util.Objects;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    private List<TvModel> dataTv;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_poster;
        TextView title_movie, release_date;
        CardView item_grid_cv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_poster = itemView.findViewById(R.id.poster);
            title_movie = itemView.findViewById(R.id.title2);
            release_date = itemView.findViewById(R.id.release_year2);
            item_grid_cv = itemView.findViewById(R.id.item_grid_cv);
        }
    }

    public TvAdapter(List<TvModel> dataTv) {
        this.dataTv = dataTv;
    }

    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder holder, int position) {
        TvModel tvModel = dataTv.get(position);

        if (tvModel.getName().equals("") || tvModel.getName() == null)
            holder.title_movie.setText("-");
        else
            holder.title_movie.setText(tvModel.getName());

        if (Objects.equals(tvModel.getFirstAirDate(), "") || tvModel.getFirstAirDate() == null)
            holder.release_date.setText("-");
        else
            holder.release_date.setText(tvModel.getFirstAirDate().substring(0, 4));

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + tvModel.getPosterPath()).centerCrop().into(holder.movie_poster);
        holder.item_grid_cv.setOnClickListener(v -> {
            FavoriteModel favoriteModel = new FavoriteModel(
                    tvModel.getId(),
                    tvModel.getName(),
                    tvModel.getFirstAirDate(),
                    tvModel.getOverview(),
                    tvModel.getPosterPath(),
                    tvModel.getBackdropPath(),
                    tvModel.getVoteAverage(),
                    DetailMovieTvActivity.TYPE_TV );

            Intent i = new Intent(holder.itemView.getContext(), DetailMovieTvActivity.class);
            i.putExtra(DetailMovieTvActivity.EXTRA_ITEM, favoriteModel);
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return dataTv.size();
    }
}
