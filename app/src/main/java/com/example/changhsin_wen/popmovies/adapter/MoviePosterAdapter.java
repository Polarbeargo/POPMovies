package com.example.changhsin_wen.popmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.example.changhsin_wen.popmovies.R;
import com.example.changhsin_wen.popmovies.util.Movie;
import android.util.Log;
import java.util.List;

/**
 * Created by changhsin-wen on 7/23/16.
 */

public class MoviePosterAdapter extends ArrayAdapter<Movie> {




    public MoviePosterAdapter(Context context) {
        super(context, 0);
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_poster, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Movie movie = getItem(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage();

        viewHolder = (ViewHolder) view.getTag();
        Log.d("Adapter","Did I get movie poster");
        Glide.with(getContext()).load(image_url).into(viewHolder.imageView);
        viewHolder.titleView.setText(movie.getTitle());

        return view;
    }




    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView titleView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.grid_item_image);
            titleView = (TextView) view.findViewById(R.id.grid_item_title);
        }
    }

}
