package com.example.h071211050_finalmobile.Api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String api_key = "d3c5aa50f6b19ca63531c8179a67f936";
    @GET("movie/now_playing?api_key=" + api_key)
    Call<MovieData> getMovie();

    @GET("tv/airing_today?api_key=" + api_key)
    Call<TvData> getTvSeries();
}
