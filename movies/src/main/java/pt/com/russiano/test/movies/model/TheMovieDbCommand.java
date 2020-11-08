package pt.com.russiano.test.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TheMovieDbCommand implements Serializable {
    // ------------------------------ FIELDS ------------------------------
    @JsonProperty
    private int page;
    @JsonProperty
    private int total_results;
    @JsonProperty
    private int total_pages;
    @JsonProperty
    @JsonUnwrapped
    private List<TheMovieDbResultCommand> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<TheMovieDbResultCommand> getResults() {
        return results;
    }

    public void setResults(List<TheMovieDbResultCommand> results) {
        this.results = results;
    }
// --------------------- GETTER / SETTER METHODS ---------------------
}

/*
            "page": 1,
            "total_results": 15,
            "total_pages": 1,
            "results": [

   public class themoviedbCommand    {
    // ------------------------------ FIELDS ------------------------------

    private int nome_filme;
    private int realizador;
    private int protagonistas;
    private  String ano_lançamento;
// --------------------- GETTER / SETTER METHODS ---------------------
}

 */