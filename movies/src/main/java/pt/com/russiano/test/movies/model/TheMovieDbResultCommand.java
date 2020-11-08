package pt.com.russiano.test.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TheMovieDbResultCommand implements Serializable {
    @JsonProperty
    private double popularity;
    @JsonProperty
    private String known_for_department;
    @JsonProperty
    private String name;
    @JsonProperty
    private int id;
    @JsonProperty
    private String profile_path;
    @JsonProperty
    private boolean adult;
    @JsonProperty
    @JsonUnwrapped
    private List<TheMovieDbKnownforCommand> known_for;  //List<themoviedbknownforCommand>
    @JsonProperty
    private int gender;

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<TheMovieDbKnownforCommand> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(List<TheMovieDbKnownforCommand> known_for) {
        this.known_for = known_for;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
//  public themoviedbResultCommand() {
  //  }
}
/*
*   "popularity": 19.801,
            "known_for_department": "Acting",
            "name": "Sylvester Stallone",
            "id": 16483,
            "profile_path": "/qDRGPAcQoW8Wuig9bvoLpHwf1gU.jpg",
            "adult": false,
            "known_for": [
* */