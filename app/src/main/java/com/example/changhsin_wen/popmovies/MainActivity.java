package com.example.changhsin_wen.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.changhsin_wen.popmovies.fragment.MainActivityFragment;
import com.example.changhsin_wen.popmovies.fragment.ReviewFragment;
import com.example.changhsin_wen.popmovies.util.Movie;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback  {

    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new ReviewFragment(),
                                ReviewFragment.TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }



    @Override
    public void onItemSelected(Movie movie) {
        if (mTwoPane) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ReviewFragment.MOVIE_REVIEW, movie);

        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_detail_container, fragment)
                .commit();
        Log.d("item", "TAp");
        } else {
            Intent intent = new Intent(this, ReviewActivity.class)
                    .putExtra(ReviewFragment.MOVIE_REVIEW, movie);
            startActivity(intent);
        }
    }
}
