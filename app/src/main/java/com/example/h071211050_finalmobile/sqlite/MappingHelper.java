package com.example.h071211050_finalmobile.sqlite;

import android.database.Cursor;

import com.example.h071211050_finalmobile.models.FavoriteModel;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<FavoriteModel> cursorToArraylist (Cursor cursor) {
        ArrayList<FavoriteModel> favouriteModels = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Database.ItemColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.TITLE));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.DATE));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.POSTER));
            String backdrop = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.BACKDROP));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.OVERVIEW));
            String vote_average = cursor.getString(cursor.getColumnIndexOrThrow(Database.ItemColumns.VOTE_AVERAGE));
            int type = cursor.getInt(cursor.getColumnIndexOrThrow(Database.ItemColumns.TYPE));
            favouriteModels.add(new FavoriteModel(id, date, title, overview, poster, backdrop, vote_average, type));
        }
        return favouriteModels;
    }
}