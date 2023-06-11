package com.example.h071211050_finalmobile.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211050_finalmobile.DetailMovieTvActivity;
import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.models.FavoriteModel;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private ArrayList<FavoriteModel> favoriteModels;
    private ActivityResultLauncher<Intent> result;

    public FavoriteAdapter(ArrayList<FavoriteModel> favoriteModels, ActivityResultLauncher<Intent> result) {
        this.favoriteModels = favoriteModels;
        this.result = result;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        FavoriteModel favoriteModel = favoriteModels.get(position);

        if (favoriteModel.getTitle().equals("") && favoriteModel.getTitle() == null)
            holder.title.setText("-");
        else
            holder.title.setText(favoriteModel.getTitle());

        if (favoriteModel.getDate().equals("") && favoriteModel.getDate() == null)
            holder.release_year.setText("-");
        else
            holder.release_year.setText(favoriteModel.getDate().substring(0, 4));

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + favoriteModel.getPoster_path()).centerCrop()
                .placeholder(R.drawable.noimage)
                .into(holder.Profil);

        if (favoriteModel.getType() == DetailMovieTvActivity.TYPE_MOVIE) {
            holder.icon_type.setImageDrawable(holder.itemView.getContext().getDrawable(R.drawable.movie));
        } else {
            holder.icon_type.setImageDrawable(holder.itemView.getContext().getDrawable(R.drawable.tv));
        }

        holder.item.setOnClickListener(v -> {
            Intent i = new Intent(holder.itemView.getContext(), DetailMovieTvActivity.class);
            i.putExtra(DetailMovieTvActivity.EXTRA_ITEM, favoriteModel);
            i.putExtra(DetailMovieTvActivity.EXTRA_TYPE, favoriteModel.getType());
            result.launch(i);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Profil, icon_type;
        TextView title, release_year;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Profil = itemView.findViewById(R.id.profil);
            icon_type = itemView.findViewById(R.id.icon_type);
            title = itemView.findViewById(R.id.title);
            release_year = itemView.findViewById(R.id.release_year);
            item = itemView.findViewById(R.id.item_cv);
        }

    }
}
