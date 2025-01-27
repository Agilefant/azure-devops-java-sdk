package org.azd.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.azd.exceptions.DefaultParametersException;

import java.util.HashMap;

import static org.azd.validators.AzDDefaultParametersValidator.validateDefaultParameters;

/**
 *  Build the request url dynamically to call Azure DevOps REST API
 *  <p>
 *      Azure DevOps REST API has many REST API implementations for all the services.
 *      It is segregated as service, resource and area.
 *      This implementation helps to call the Azure DevOps REST API with url formed dynamically
 *      by passing the correct service, resource and area for respective API
 *  </p>
 *  @author Harish Karthic
 */
public class Url {

    private final String INSTANCE = "https://dev.azure.com/";
    private final AzDDefaultParameters DEFAULT_PARAMETERS;
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final String LOCATION_URL_VERSION = "5.0-preview.1";


    /***
     * This class expects the instance of AzDDefaultParameters to create the request url
     * @param defaultParameters instance of AzDDefaultParameters
     */
    public Url(AzDDefaultParameters defaultParameters) {
        this.DEFAULT_PARAMETERS = defaultParameters;
    }


    /**
     *  Gets the resource area url based on resource id passed for the organization
     * @param resourceID pass the resource id
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @throws JsonProcessingException If invalid json primitive is encountered.
     * @return resource area url
     */
    private String getLocationUrl(String resourceID) throws DefaultParametersException, JsonProcessingException {

        if (DEFAULT_PARAMETERS.getOrganization() == null) { validateDefaultParameters(); }

        String url = new StringBuilder().append(INSTANCE)
                .append(DEFAULT_PARAMETERS.getOrganization())
                .append("/_apis/resourceAreas/")
                .append(resourceID)
                .append("?api-preview=")
                .append(LOCATION_URL_VERSION)
                .toString();

        String r = MAPPER.readValue(RequestAPI.get(url), LocationUrl.class).getLocationUrl();
        return r.replaceAll("/$","");
    }

    /**
     *  Builds the request url dynamically for the passed service, resource and area
     * @param resourceId pass the resource id
     * @param project pass the project name
     * @param area area of the REST API e.g., Release
     * @param id id of any entity to pass in
     * @param resource pass the resource entity e.g., Releases
     * @param apiVersion pass the API version
     * @param queryString pass the query string to form the url
     * @throws DefaultParametersException user must instantiate AzDDefaultParameters before calling this method
     * @return resource area url
     */
    public String buildRequestUrl(
            String resourceId,
            String project,
            String area,
            String id,
            String resource,
            String apiVersion,
            HashMap<String, Object> queryString) throws DefaultParametersException, JsonProcessingException {
        // build the request url to dynamically serve the API requests

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((getLocationUrl(resourceId)));

        if (project != null) {
            stringBuilder.append("/").append(project);
        }

        stringBuilder.append("/_apis");

        if (area != null) {
            stringBuilder.append("/").append(area);
        }
        if (id != null) {
            stringBuilder.append("/").append(id);
        }
        if (resource != null) {
            stringBuilder.append("/").append(resource);
        }
        stringBuilder.append("?api-version=").append(apiVersion);
        if (queryString != null) {
            for (var key : queryString.keySet()) {
                stringBuilder.append(getQueryString(key, queryString.get(key)));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Helps to create a query string from given key and value
     * @param key pass the key of the HashMap
     * @param value pass the value of the HasMap
     * @return query string
     */
    private String getQueryString(String key, Object value) {
        return "&" + key + "=" + value;
    }
}