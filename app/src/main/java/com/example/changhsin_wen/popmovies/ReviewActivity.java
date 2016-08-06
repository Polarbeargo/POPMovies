package com.example.changhsin_wen.popmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.changhsin_wen.popmovies.fragment.ReviewFragment;

/**
 * Created by changhsin-wen on 7/23/16.
 */

public class ReviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ReviewFragment.MOVIE_REVIEW,
                    getIntent().getParcelableExtra(ReviewFragment.MOVIE_REVIEW));

            ReviewFragment fragment = new ReviewFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }
}
