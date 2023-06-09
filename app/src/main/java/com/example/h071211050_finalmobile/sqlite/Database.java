package com.example.h071211050_finalmobile.sqlite;

import android.provider.BaseColumns;

public class Database {
    public static final String DATABASE_NAME = "simplamovieapps.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "favourite_table";
    public static final class ItemColumns implements BaseColumns {
        public static final String TITLE = "title";
        public static final String DATE = "release_date";
        public static final String OVERVIEW = "overview";
        public static final String POSTER = "poster";
        public static final String BACKDROP = "backdrop";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String TYPE = "type";

    }
}