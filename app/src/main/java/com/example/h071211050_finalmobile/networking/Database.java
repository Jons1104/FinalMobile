package com.example.h071211050_finalmobile.networking;

import android.provider.BaseColumns;

public class Database {
    public static final String DATABASE_NAME = "h071211050_finalmobile.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "favourite_table";
    public static final class ItemColumns implements BaseColumns {
        public static final String TITLE = "title";
        public static final String DATE = "release_date";
        public static final String OVERVIEW = "overview";
        public static final String POSTER_PATH = "poster_path";
        public static final String BACKDROP_PATH = "backdrop_path";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String TYPE = "type";

    }
}