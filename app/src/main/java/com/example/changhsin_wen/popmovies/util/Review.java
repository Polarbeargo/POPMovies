package com.example.changhsin_wen.popmovies.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changhsin-wen on 7/30/16.
 */

public class Review {

    private String id;
    private String author;
    private String content;

    public Review() {

    }

    public Review(JSONObject trailer) throws JSONException {
        this.id = trailer.getString("id");
        this.author = trailer.getString("author");
        this.content = trailer.getString("content");
    }

    public String getId() { return id; }

    public String getAuthor() { return author; }

    public String getContent() { return content; }
}
