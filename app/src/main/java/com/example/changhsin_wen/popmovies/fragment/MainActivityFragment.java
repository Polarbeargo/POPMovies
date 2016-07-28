package com.example.changhsin_wen.popmovies.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.AdapterView;
import java.util.ArrayList;
import android.util.Log;
import android.os.AsyncTask;

import com.example.changhsin_wen.popmovies.ReviewActivity;
import com.example.changhsin_wen.popmovies.util.Movie;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.changhsin_wen.popmovies.R;
import com.example.changhsin_wen.popmovies.adapter.MoviePosterAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainActivityFragment extends Fragment {

    private GridView mGridView;
    private MoviePosterAdapter mMoviePosterAdapter;

    private static final String SORT_SETTING = "sort_setting";
    private static final String POPULARITY = "popularity.desc";
    private static final String MOVIES = "movies";
    private static final String FAVORITE = "favorite";
    private static final String RATING = "vote_average.desc";



    private String mSortBy = POPULARITY;

    private ArrayList<Movie> mMovies = null;

    private OnFragmentInteractionListener mListener;

    public MainActivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainActivityFragment newInstance(String param1, String param2) {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_main,menu);
        MenuItem sort_by_popularity = menu.findItem(R.id.sort_by_popularity);
        MenuItem sort_by_rating = menu.findItem(R.id.sort_by_rating);
        if (mSortBy.contentEquals(POPULARITY)) {
            if (!sort_by_popularity.isChecked())
                 sort_by_popularity.setChecked(true);
        }
        else {
            if (!sort_by_rating.isChecked())
                 sort_by_rating.setChecked(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.sort_by_popularity:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                mSortBy = POPULARITY;
                updateMovies(mSortBy);
                return true;
            case R.id.sort_by_rating:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                mSortBy = RATING;
                updateMovies(mSortBy);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mGridView = (GridView) view.findViewById(R.id.gridview_movies);

        mMoviePosterAdapter = new MoviePosterAdapter(getActivity());

        mGridView.setAdapter(mMoviePosterAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = mMoviePosterAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), ReviewActivity.class)
                        .putExtra(ReviewFragment.MOVIE_REVIEW, movie);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SORT_SETTING)) {
                mSortBy = savedInstanceState.getString(SORT_SETTING);
            }

            if (savedInstanceState.containsKey(MOVIES)) {
                mMovies = savedInstanceState.getParcelableArrayList(MOVIES);
                for (Movie movie : mMovies) {
                    mMoviePosterAdapter.add(movie);
                }
            } else {
                updateMovies(mSortBy);
            }
        } else {
            updateMovies(mSortBy);
        }

        return view;
    }

    private void updateMovies(String sort_by) {
            FetchMoviesTask moviesTask = new FetchMoviesTask();
            moviesTask.execute(sort_by);


    }
    @Override
    public void onSaveInstanceState(Bundle bundle) {
        if (!mSortBy.contentEquals(POPULARITY)) {
            bundle.putString(SORT_SETTING, mSortBy);
        }
        if (mMovies != null) {
            bundle.putParcelableArrayList(MOVIES, mMovies);
        }
        super.onSaveInstanceState(bundle);
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    public interface Callback {
        void onItemSelected(Movie movie);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

        private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

        private List<Movie> getMoviesDataFromJson(String jsonStr) throws JSONException {
            JSONObject movieJson = new JSONObject(jsonStr);
            JSONArray movieArray = movieJson.getJSONArray("results");

            List<Movie> results = new ArrayList<>();

            for(int i = 0; i < movieArray.length(); i++) {
                JSONObject movie = movieArray.getJSONObject(i);
                Movie movieModel = new Movie(movie);
                results.add(movieModel);
            }

            return results;
        }

        @Override
        protected List<Movie> doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String jsonStr = null;

            try {
                final String BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
                final String SORT_BY_PARAM = "sort_by";
                final String API_KEY_PARAM = "api_key";

                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(SORT_BY_PARAM, params[0])
                        .appendQueryParameter(API_KEY_PARAM, getString(R.string.tmdb_api_key))
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                jsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getMoviesDataFromJson(jsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            if (movies != null) {
                if (mMoviePosterAdapter != null) {
                    mMoviePosterAdapter.clear();
                    for (Movie movie : movies) {
                        mMoviePosterAdapter.add(movie);
                    }
                }
                mMovies = new ArrayList<>();
                mMovies.addAll(movies);
            }
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
