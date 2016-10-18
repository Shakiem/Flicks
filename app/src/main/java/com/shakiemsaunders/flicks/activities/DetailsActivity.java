package com.shakiemsaunders.flicks.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shakiemsaunders.flicks.R;
import com.shakiemsaunders.flicks.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends ActionBarActivity {

    private ImageView imageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView overview;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);

        imageView = (ImageView) findViewById(R.id.imageView);
        titleTextView = (TextView) findViewById(R.id.titleText);
        releaseDateTextView = (TextView) findViewById(R.id.releaseText);
        overview = (TextView) findViewById(R.id.overviewText);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Movie movie = (Movie) getIntent().getSerializableExtra(MovieActivity.MOVIE);

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            Picasso.with(getBaseContext()).load(movie.getBackdropPath())
                    .placeholder(R.drawable.movie_clip).resize(900,0)
                    .error(R.drawable.movie_clip_error).into(imageView);
        }
        else if(orientation == Configuration.ORIENTATION_PORTRAIT){
            Picasso.with(getBaseContext()).load(movie.getBackdropPath())
                    .placeholder(R.drawable.movie_clip).resize(700,0)
                    .error(R.drawable.movie_clip_error).into(imageView);
        }

        titleTextView.setText(movie.getOriginalTitle());
        releaseDateTextView.setText(movie.getReleaseDate());

        overview.setText(movie.getOverview());

        ratingBar.setRating((float)movie.getVoteAverage()/2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
