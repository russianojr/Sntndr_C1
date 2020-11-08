package pt.com.russiano.test.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TheMovieDbKnownforCommand implements Serializable {


    @JsonProperty
    private int vote_count;
    @JsonProperty
    private int id;
    @JsonProperty
    private boolean video;
    @JsonProperty
    private String media_type;
    @JsonProperty
    private Double vote_average;
    @JsonProperty
    private String title;
    @JsonProperty
    private String release_date;
    @JsonProperty
    private String original_language;
    @JsonProperty
    private String original_title;
    @JsonProperty
    @JsonUnwrapped
    private int[] genre_ids;
    @JsonProperty
    private String backdrop_path;
    @JsonProperty
    private boolean adult;
    @JsonProperty
    private String overview;
    @JsonProperty
    private String poster_path;

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


}
/*
                    "vote_count": 15587,
                    "id": 283995,
                    "video": false,
                    "media_type": "movie",
                    "vote_average": 7.6,
                    "title": "Guardians of the Galaxy Vol. 2",
                    "release_date": "2017-04-19",
                    "original_language": "en",
                    "original_title": "Guardians of the Galaxy Vol. 2",
                    "genre_ids": [
                        28,
                        12,
                        35,
                        878
                    ],
                    "backdrop_path": "/aJn9XeesqsrSLKcHfHP4u5985hn.jpg",
                    "adult": false,
                    "overview": "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",
                    "poster_path": "/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg"
* */