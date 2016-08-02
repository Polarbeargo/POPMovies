package com.example.changhsin_wen.popmovies.util;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.changhsin_wen.popmovies.fragment.MainActivityFragment;
/**
 * Created by changhsin-wen on 7/23/16.
 */

public class Movie implements Parcelable{
    private int id;
    private String title; // original_title
    private String image; // poster_path
    private String image2; // backdrop_path
    private String overview;
    private int rating; // vote_average
    private String date; // release_date

    public Movie() {

    }

    public Movie(JSONObject movie) throws JSONException {
        this.id = movie.getInt("id");
        this.title = movie.getString("original_title");
        this.image = movie.getString("poster_path");
        this.image2 = movie.getString("backdrop_path");
        this.overview = movie.getString("overview");
        this.rating = movie.getInt("vote_average");
        this.date = movie.getString("release_date");
    }
    public Movie(Cursor cursor) {
        this.id = cursor.getInt(MainActivityFragment.COL_MOVIE_ID);
        this.title = cursor.getString(MainActivityFragment.COL_TITLE);
        this.image = cursor.getString(MainActivityFragment.COL_IMAGE);
        this.image2 = cursor.getString(MainActivityFragment.COL_IMAGE2);
        this.overview = cursor.getString(MainActivityFragment.COL_OVERVIEW);
        this.rating = cursor.getInt(MainActivityFragment.COL_RATING);
        this.date = cursor.getString(MainActivityFragment.COL_DATE);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel dest) {
            return new Movie(dest);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private Movie(Parcel dest) {
        id = dest.readInt();
        title = dest.readString();
        image = dest.readString();
        image2 = dest.readString();
        overview = dest.readString();
        rating = dest.readInt();
        date = dest.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(image2);
        dest.writeString(overview);
        dest.writeInt(rating);
        dest.writeString(date);
    }
    public  int getId(){return id;}
    public String getImage() {
        return image;
    }
    public String getImage2() {
        return image2;
    }
    public String getTitle() {
        return title;
    }
    public int getRating() {
        return rating;
    }
    public String getDate() {
        return date;
    }
    public String getOverview() {
        return overview;
    }
}

