package com.example.changhsin_wen.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.changhsin_wen.popmovies.fragment.MainActivityFragment;
import com.example.changhsin_wen.popmovies.fragment.ReviewFragment;
import com.example.changhsin_wen.popmovies.util.Movie;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback  {

    private boolean mReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.movie_review_container) != null) {
            mReview = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_review_container, new ReviewFragment(),
                                ReviewFragment.TAG)
                        .commit();
            }
        } else {
            mReview = false;
        }
    }

    @Override
    public void onItemSelected(Movie movie) {
        if (mReview) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ReviewFragment.MOVIE_REVIEW, movie);

            ReviewFragment fragment = new ReviewFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_review_container, fragment, ReviewFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, ReviewActivity.class)
                    .putExtra(ReviewFragment.MOVIE_REVIEW, movie);
            startActivity(intent);
        }
    }
}
