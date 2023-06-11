package com.example.h071211050_finalmobile.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.h071211050_finalmobile.MainActivity;
import com.example.h071211050_finalmobile.data.MovieData;
import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.adapter.MovieAdapter;
import com.example.h071211050_finalmobile.api.ApiConfig;
import com.example.h071211050_finalmobile.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private RecyclerView ListMovie;
    private ProgressBar MovieProgressbar;
    private TextView Error;
    private MovieAdapter movieAdapter;
    private List<MovieModel> dataMovie = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(MovieFragment.class.getSimpleName(), "masuk home fragment");

        ListMovie = view.findViewById(R.id.listMovie);
        MovieProgressbar = view.findViewById(R.id.movie_progressbar);
        Error = view.findViewById(R.id.error);


        if (MainActivity.actionBar != null) {
            Log.d(TAG, "join require Action bar");
            MainActivity.actionBar.setTitle("Movies");
        }

        ListMovie.setHasFixedSize(true);
        ListMovie.setLayoutManager(new GridLayoutManager(getContext(), 2));
        setDataMovie();
    }

    private void setDataMovie() {
        Call<MovieData> movieClient = ApiConfig.getApiService().getMovie();
        movieClient.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                if(response.isSuccessful()){
                    if (response.body().getData() != null) {
                        MovieProgressbar.setVisibility(View.GONE);
                        dataMovie = response.body().getData();
                        Log.d("MainActivity", "Movie list");
                        for (MovieModel movieDataResponse : dataMovie) {
                            Log.d("MainActivity", ": " + movieDataResponse.getTitle());
                        }
                        movieAdapter = new MovieAdapter(dataMovie);
                        ListMovie.setAdapter(movieAdapter);
                    } else {
                        Log.e("MainActivity", "data body is null " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getMessage());
                Error.setVisibility(View.VISIBLE);
            }
        });
    }

}