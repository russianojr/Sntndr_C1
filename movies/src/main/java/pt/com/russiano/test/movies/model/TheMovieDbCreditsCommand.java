package pt.com.russiano.test.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TheMovieDbCreditsCommand implements Serializable {

    @JsonProperty
    private int id;

    @JsonProperty
    @JsonUnwrapped
    private List<TheMovieDbCreditsCastCommand> cast;

    @JsonProperty
    @JsonUnwrapped
    private List<TheMovieDbCreditsCrewCommand> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TheMovieDbCreditsCastCommand> getCast() {
        return cast;
    }

    public void setCast(List<TheMovieDbCreditsCastCommand> cast) {
        this.cast = cast;
    }

    public List<TheMovieDbCreditsCrewCommand> getCrew() {
        return crew;
    }

    public void setCrew(List<TheMovieDbCreditsCrewCommand> crew) {
        this.crew = crew;
    }
}

