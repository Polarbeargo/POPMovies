package com.example.changhsin_wen.popmovies.fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.changhsin_wen.popmovies.util.Movie;
import com.example.changhsin_wen.popmovies.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {
    public static final String TAG = "ReviewFragment";
    public static final String MOVIE_REVIEW = "MOVIE_REVIEW";

    private Movie mMovie;

    ImageView detailImage;

    TextView detailTitle;

    TextView detailOverview;

    TextView detailDate;

    TextView detailVoteAverage;

    ScrollView reviewLayout;



    private OnFragmentInteractionListener mListener;

    public ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mMovie = arguments.getParcelable(ReviewFragment.MOVIE_REVIEW);
        }

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        detailImage = (ImageView) view.findViewById(R.id.detail_image);
        detailTitle = (TextView) view.findViewById(R.id.detail_title);
        detailOverview = (TextView) view.findViewById(R.id.detail_overview);
        detailDate = (TextView) view.findViewById(R.id.detail_date);
        detailVoteAverage = (TextView) view.findViewById(R.id.detail_vote_average);

        String image_url = "http://image.tmdb.org/t/p/w342" + mMovie.getImage2();
        Glide.with(this).load(image_url).into(detailImage);

        detailTitle.setText(mMovie.getTitle());
        detailOverview.setText(mMovie.getOverview());

        String movie_date = mMovie.getDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String date = DateUtils.formatDateTime(getActivity(),
                    format.parse(movie_date).getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
            detailDate.setText(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        detailVoteAverage.setText(Integer.toString(mMovie.getRating()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
