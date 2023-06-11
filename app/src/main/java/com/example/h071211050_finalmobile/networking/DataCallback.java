package com.example.h071211050_finalmobile.networking;

import com.example.h071211050_finalmobile.models.FavoriteModel;

import java.util.ArrayList;

public interface DataCallback {
    void postExecute(ArrayList<FavoriteModel> items);
}
