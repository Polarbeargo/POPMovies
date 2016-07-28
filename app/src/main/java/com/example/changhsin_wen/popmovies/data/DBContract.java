package com.example.changhsin_wen.popmovies.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.content.ContentResolver;
/**
 * Created by changhsin-wen on 7/23/16.
 */

public class DBContract {

    public static final String CONTENT_AUTHORITY = "com.example.changhsin_wen.popmovies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "movie";

    public  static final class MovieEntry implements BaseColumns{

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_IMAGE_COLUMN2 = "image2";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_DATE = "date";

        public static Uri movieUriBuild ( long id){
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }
    }
}
