package com.example.h071211050_finalmobile;

import com.example.h071211050_finalmobile.models.MovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieData {
    @SerializedName("results")
    private List<MovieModel> results;
    public List<MovieModel> getData() {
        return results;
    }
}
