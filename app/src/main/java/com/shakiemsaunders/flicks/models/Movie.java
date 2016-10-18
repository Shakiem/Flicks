package com.shakiemsaunders.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private String posterPath;
    private String backdropPath;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private double voteAverage;

    public static final String POSTER_PATH_KEY = "poster_path";
    public static final String BACKDROP_PATH_KEY = "backdrop_path";
    public static final String ORIGINAL_TITLE_KEY = "original_title";
    public static final String OVERVIEW_KEY = "overview";
    public static final String RELEASE_DATE_KEY = "release_date";
    public static final String VOTE_AVERAGE_KEY = "vote_average";

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString(POSTER_PATH_KEY);
        this.originalTitle = jsonObject.getString(ORIGINAL_TITLE_KEY);
        this.overview = jsonObject.getString(OVERVIEW_KEY);
        this.backdropPath = jsonObject.getString(BACKDROP_PATH_KEY);
        this.releaseDate = jsonObject.getString(RELEASE_DATE_KEY);
        this.voteAverage = jsonObject.getDouble(VOTE_AVERAGE_KEY);
    }

    public static List<Movie> parseMovieList(JSONArray resultArray){
        List<Movie> results = new ArrayList<>();

        for (int i = 0; i < resultArray.length(); ++i){
            try {
                results.add(new Movie(resultArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
