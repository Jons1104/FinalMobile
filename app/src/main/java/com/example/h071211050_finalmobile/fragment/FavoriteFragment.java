package com.example.h071211050_finalmobile.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211050_finalmobile.MainActivity;
import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.adapter.FavoriteAdapter;
import com.example.h071211050_finalmobile.models.FavoriteModel;
import com.example.h071211050_finalmobile.networking.DataAsynchronous;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private RecyclerView ListFavorite;
    private TextView Error;
    private ProgressBar FavoriteProgressbar;
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteModel> favoriteModel = new ArrayList<>();
    private ActivityResultLauncher<Intent> result = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                loadAllFavorite();
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListFavorite = view.findViewById(R.id.listFavorite);
        Error = view.findViewById(R.id.error);
        FavoriteProgressbar = view.findViewById(R.id.fav_progressbar);

        if (MainActivity.actionBar != null) {
            Log.d(TAG, "join require Action bar");
            MainActivity.actionBar.setTitle("Favorite");
        }

        ListFavorite.setHasFixedSize(true);
        ListFavorite.setLayoutManager(new LinearLayoutManager(this.getContext()));
        loadAllFavorite();
    }

    private void loadAllFavorite() {
        new DataAsynchronous(this.getContext(), favoriteModels -> {
            if (favoriteModels.size() > 0) {
                this.favoriteModel.addAll(favoriteModels);
                favoriteAdapter = new FavoriteAdapter(favoriteModels, result);
                ListFavorite.setAdapter(favoriteAdapter);
                Error.setVisibility(View.GONE);
                FavoriteProgressbar.setVisibility(View.GONE);
            } else {
                favoriteAdapter = new FavoriteAdapter(favoriteModels, result);
                ListFavorite.setAdapter(favoriteAdapter);
                Error.setVisibility(View.VISIBLE);
                FavoriteProgressbar.setVisibility(View.GONE);
            }
        }).execute();
    }
}