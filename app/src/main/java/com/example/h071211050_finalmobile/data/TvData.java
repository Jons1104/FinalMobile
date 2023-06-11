package com.example.h071211050_finalmobile.data;

import com.example.h071211050_finalmobile.models.TvModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvData {
    @SerializedName("results")
    private List<TvModel> results;
    public List<TvModel> getData() {
        return results;
    }
}
