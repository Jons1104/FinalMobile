package com.example.h071211050_finalmobile.Api;

import com.example.h071211050_finalmobile.TvResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvData {
    @SerializedName("results")
    private List<TvResponse> results;
    public List<TvResponse> getData() { return results; }
}
