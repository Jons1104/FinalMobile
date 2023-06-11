package com.example.h071211050_finalmobile.api;

import com.example.h071211050_finalmobile.data.MovieData;
import com.example.h071211050_finalmobile.data.TvData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie/now_playing?api_key=76825d159c530bdb2a1677af1fd7d5d7")
    Call<MovieData> getMovie();

    @GET("tv/top_rated?api_key=76825d159c530bdb2a1677af1fd7d5d7")
    Call<TvData> getTv();
}
