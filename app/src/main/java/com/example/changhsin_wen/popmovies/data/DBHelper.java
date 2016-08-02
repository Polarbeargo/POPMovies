package com.example.changhsin_wen.popmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by changhsin-wen on 7/23/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "movie.db";

     public DBHelper(Context context){

         super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + DBContract.MovieEntry.TABLE_NAME + " (" +
                DBContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                DBContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                DBContract.MovieEntry.COLUMN_IMAGE + " TEXT, " +
                DBContract.MovieEntry.COLUMN_IMAGE2 + " TEXT, " +
                DBContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
                DBContract.MovieEntry.COLUMN_RATING + " INTEGER, " +
                DBContract.MovieEntry.COLUMN_DATE + " TEXT);";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
