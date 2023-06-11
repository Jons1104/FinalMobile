package com.example.h071211050_finalmobile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.h071211050_finalmobile.R;

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

    }
}