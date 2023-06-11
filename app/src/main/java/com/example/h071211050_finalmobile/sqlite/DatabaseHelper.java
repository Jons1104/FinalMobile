package com.example.h071211050_finalmobile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE_FAVOURITE_QUERY =
            String.format(
                    "CREATE TABLE %S"
                            + "(%s INTEGER NOT NULL PRIMARY KEY,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL)",
                    Database.TABLE_NAME,
                    Database.ItemColumns._ID,
                    Database.ItemColumns.TITLE,
                    Database.ItemColumns.DATE,
                    Database.ItemColumns.OVERVIEW,
                    Database.ItemColumns.POSTER_PATH,
                    Database.ItemColumns.BACKDROP_PATH,
                    Database.ItemColumns.VOTE_AVERAGE,
                    Database.ItemColumns.TYPE

            );


    public DatabaseHelper(@Nullable Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVOURITE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE_NAME);
        onCreate(db);
    }
}