package org.azd.git.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequests {
    @JsonProperty("value")
    private List<PullRequest> value;

    @Override
    public String toString() {
        return "PullRequests{" +
                "value=" + value +
                '}';
    }

    public List<PullRequest> getPullRequests() {
        return value;
    }

    public void setPullRequests(List<PullRequest> value) {
        this.value = value;
    }

}
