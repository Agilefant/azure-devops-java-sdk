package org.azd.release.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.azd.common.Author;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReleaseApprovalHistory {
    @JsonProperty("approver")
    private Author approver;
    @JsonProperty("changedBy")
    private Author changedBy;
    @JsonProperty("comments")
    private String comments;
    @JsonProperty("createdOn")
    private String createdOn;
    @JsonProperty("modifiedOn")
    private String modifiedOn;
    @JsonProperty("revision")
    private int revision;

    public Author getApprover() {
        return approver;
    }

    public void setApprover(Author approver) {
        this.approver = approver;
    }

    public Author getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(Author changedBy) {
        this.changedBy = changedBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    @Override
    public String toString() {
        return "ReleaseApprovalHistory{" +
                "approver=" + approver +
                ", changedBy=" + changedBy +
                ", comments='" + comments + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", modifiedOn='" + modifiedOn + '\'' +
                ", revision=" + revision +
                '}';
    }
}
