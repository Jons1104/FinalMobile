package com.example.h071211050_finalmobile;

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

import com.example.h071211050_finalmobile.R;
import com.example.h071211050_finalmobile.adapter.MovieAdapter;
import com.example.h071211050_finalmobile.Api.ApiConfig;
import com.example.h071211050_finalmobile.Api.MovieData;
import com.example.h071211050_finalmobile.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private RecyclerView listMovie;
    private ProgressBar progressbar;
    private TextView error;

    private List<MovieResponse> dataMovie = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(MovieFragment.class.getSimpleName(), "Welcome Home Fragment");
        listMovie = view.findViewById(R.id.listmovie);
        error = view.findViewById(R.id.error);
        progressbar = view.findViewById(R.id.progressbar);

        listMovie.setHasFixedSize(true);
        listMovie.setLayoutManager(new GridLayoutManager(getContext(), 2));

        setDataMovie();
    }

    private void setDataMovie() {
        Call<MovieData> movieClient = ApiConfig.getApiService().getMovie();
        movieClient.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                if(response.isSuccessful()){
                    if (response.body().getData() != null) {
                        progressbar.setVisibility(View.GONE);
                        dataMovie = response.body().getData();
                        Log.d("MainActivity", "movie list");
                        for (MovieResponse movieDataResponse : dataMovie) {
                            Log.d("MainActivity", " " + movieDataResponse.getTitle());
                        }
                        MovieAdapter movieAdapter = new MovieAdapter(dataMovie);
                        listMovie.setAdapter(movieAdapter);
                    } else {
                        Log.e("MainActivity", "data body is null " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.d("MainActivity", " " + t.getMessage());
                error.setVisibility(View.VISIBLE);
            }
        });
    }
}