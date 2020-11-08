package pt.com.russiano.test.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class NytimesCommand {

    @JsonProperty
    private String status;

    @JsonProperty
    private String copyright;

    @JsonProperty
    private boolean has_more;

    @JsonProperty
    private int num_results;

    @JsonProperty
    @JsonUnwrapped
    private List<NytimesResultsCommand> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public List<NytimesResultsCommand> getResults() {
        return results;
    }
}
