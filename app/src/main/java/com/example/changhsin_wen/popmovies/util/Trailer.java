package com.example.changhsin_wen.popmovies.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changhsin-wen on 7/30/16.
 */

public class Trailer {

    private String id;
    private String key;
    private String name;
    private String site;
    private String type;

    public Trailer() {

    }

    public Trailer(JSONObject trailer) throws JSONException {
        this.id = trailer.getString("id");
        this.key = trailer.getString("key");
        this.name = trailer.getString("name");
        this.site = trailer.getString("site");
        this.type = trailer.getString("type");
    }

    public String getId() {
        return id;
    }

    public String getKey() { return key; }

    public String getName() { return name; }

    public String getSite() { return site; }

    public String getType() { return type; }
}
