package com.example.h071211050_finalmobile.networking;

import android.database.Cursor;

import com.example.h071211050_finalmobile.models.FavoriteModel;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<FavoriteModel> cursorToArraylist (Cursor cursor) {
        ArrayList<FavoriteModel> favoriteModels = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Database.ItemColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.TITLE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.DATE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.OVERVIEW));
            String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.POSTER_PATH));
            String backdrop_path = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.BACKDROP_PATH));
            String vote_average = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.VOTE_AVERAGE));
            int type = cursor.getInt(cursor.getColumnIndexOrThrow(Database.ItemColumns.TYPE));
            favoriteModels.add(new FavoriteModel(id, title, date, overview, poster_path, backdrop_path, vote_average, type));
        }
        return favoriteModels;
    }
}