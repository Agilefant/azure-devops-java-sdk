package org.azd.workitemtracking.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/***
 * List of work item types
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkItemTypes {
    /***
     * List of work item types
     */
    @JsonProperty("value")
    private List<WorkItemType> workItemTypes;

    public List<WorkItemType> getWorkItemTypes() {
        return workItemTypes;
    }

    public void setWorkItemTypes(List<WorkItemType> workItemTypes) {
        this.workItemTypes = workItemTypes;
    }

    @Override
    public String toString() {
        return "WorkItemTypes{" +
                "workItemTypes=" + workItemTypes +
                '}';
    }
}