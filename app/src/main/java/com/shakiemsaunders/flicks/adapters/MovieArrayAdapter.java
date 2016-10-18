package com.shakiemsaunders.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shakiemsaunders.flicks.R;
import com.shakiemsaunders.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        MovieHolder movieHolder;

        if(convertView == null){
            movieHolder = new MovieHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            movieHolder.title = (TextView) convertView.findViewById(R.id.titleTextView);
            movieHolder.overView = (TextView) convertView.findViewById(R.id.overviewTextView);
            movieHolder.imageView = (ImageView) convertView.findViewById(R.id.movieImageView);
            convertView.setTag(movieHolder);
        }
        else{
            movieHolder = (MovieHolder) convertView.getTag();
        }

        movieHolder.title.setText(movie.getOriginalTitle());
        movieHolder.overView.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            Picasso.with(getContext()).load(movie.getPosterPath())
                .placeholder(R.drawable.movie_clip)
                .error(R.drawable.movie_clip_error)
                .into(movieHolder.imageView);
        }
        else
        {
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .resize(500, 0).placeholder(R.drawable.movie_clip)
                    .error(R.drawable.movie_clip_error)
                    .into(movieHolder.imageView);
        }



        return convertView;
    }

    private class MovieHolder{
        ImageView imageView;
        TextView title;
        TextView overView;

    }
}
