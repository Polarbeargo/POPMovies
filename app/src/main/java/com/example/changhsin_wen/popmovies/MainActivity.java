package com.example.changhsin_wen.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.changhsin_wen.popmovies.fragment.MainActivityFragment;
import com.example.changhsin_wen.popmovies.fragment.ReviewFragment;
import com.example.changhsin_wen.popmovies.util.Movie;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

