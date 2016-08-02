package com.example.changhsin_wen.popmovies.util;

import android.content.Context;
import android.database.Cursor;
import com.example.changhsin_wen.popmovies.data.DBContract;
/**
 * Created by changhsin-wen on 7/30/16.
 */

public class Utility {
    public static int isFavorited(Context context, int id) {
        Cursor cursor = context.getContentResolver().query(
                DBContract.MovieEntry.CONTENT_URI,
                null,   // projection
                DBContract.MovieEntry.COLUMN_MOVIE_ID + " = ?", // selection
                new String[] { Integer.toString(id) },   // selectionArgs
                null    // sort order
        );
        int numRows = cursor.getCount();
        cursor.close();
        return numRows;
    }

    public static String buildImageUrl(int width, String fileName) {
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + fileName;
    }
}
