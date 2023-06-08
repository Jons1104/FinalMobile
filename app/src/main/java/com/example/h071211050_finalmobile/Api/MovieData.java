package com.example.h071211050_finalmobile.Api;

import com.example.h071211050_finalmobile.MovieResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieData {
    @SerializedName("results")
    private List<MovieResponse> results;
    public List<MovieResponse> getData() { return results; }
}
