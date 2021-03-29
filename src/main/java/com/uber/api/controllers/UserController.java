/*
 * UberAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */

package com.uber.api.controllers;

import com.uber.api.ApiHelper;
import com.uber.api.AuthManager;
import com.uber.api.Configuration;
import com.uber.api.exceptions.ApiException;
import com.uber.api.exceptions.ErrorException;
import com.uber.api.http.Headers;
import com.uber.api.http.client.HttpCallback;
import com.uber.api.http.client.HttpClient;
import com.uber.api.http.client.HttpContext;
import com.uber.api.http.request.HttpRequest;
import com.uber.api.http.response.HttpResponse;
import com.uber.api.http.response.HttpStringResponse;
import com.uber.api.models.Activities;
import com.uber.api.models.Profile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * This class lists all the endpoints of the groups.
 */
public final class UserController extends BaseController {

    /**
     * Initializes the controller.
     * @param config    Configurations added in client.
     * @param httpClient    Send HTTP requests and read the responses.
     * @param authManagers    Apply authorization to requests.
     */
    public UserController(Configuration config, HttpClient httpClient,
            Map<String, AuthManager> authManagers) {
        super(config, httpClient, authManagers);
    }

    /**
     * Initializes the controller with HTTPCallback.
     * @param config    Configurations added in client.
     * @param httpClient    Send HTTP requests and read the responses.
     * @param authManagers    Apply authorization to requests.
     * @param httpCallback    Callback to be called before and after the HTTP call.
     */
    public UserController(Configuration config, HttpClient httpClient,
            Map<String, AuthManager> authManagers, HttpCallback httpCallback) {
        super(config, httpClient, authManagers, httpCallback);
    }

    /**
     * The User Profile endpoint returns information about the Uber user that has authorized with
     * the application.
     * @return    Returns the Profile response from the API call
     * @throws    ApiException    Represents error response from the server.
     * @throws    IOException    Signals that an I/O exception of some sort has occurred.
     */
    public Profile userProfile() throws ApiException, IOException {
        HttpRequest request = buildUserProfileRequest();
        HttpResponse response = getClientInstance().execute(request, false);
        HttpContext context = new HttpContext(request, response);

        return handleUserProfileResponse(context);
    }

    /**
     * The User Profile endpoint returns information about the Uber user that has authorized with
     * the application.
     * @return    Returns the Profile response from the API call
     */
    public CompletableFuture<Profile> userProfileAsync() {
        return makeHttpCallAsync(() -> buildUserProfileRequest(),
            request -> getClientInstance().executeAsync(request, false),
            context -> handleUserProfileResponse(context));
    }

    /**
     * Builds the HttpRequest object for userProfile.
     */
    private HttpRequest buildUserProfileRequest() {
        //the base uri for api requests
        String baseUri = config.getBaseUri();

        //prepare query string for API call
        final StringBuilder queryBuilder = new StringBuilder(baseUri
                + "/me");

        //load all headers for the outgoing API request
        Headers headers = new Headers();
        headers.add("user-agent", BaseController.userAgent);
        headers.add("accept", "application/json");

        //prepare and invoke the API call request to fetch the response
        HttpRequest request = getClientInstance().get(queryBuilder, headers, null, null);

        // Invoke the callback before request if its not null
        if (getHttpCallback() != null) {
            getHttpCallback().onBeforeRequest(request);
        }

        return request;
    }

    /**
     * Processes the response for userProfile.
     * @return An object of type Profile
     */
    private Profile handleUserProfileResponse(
            HttpContext context) throws ApiException, IOException {
        HttpResponse response = context.getResponse();

        //invoke the callback after response if its not null
        if (getHttpCallback() != null) {
            getHttpCallback().onAfterResponse(context);
        }

        //Error handling using HTTP status codes
        int responseCode = response.getStatusCode();

        if ((responseCode < 200) || (responseCode > 208)) {
            throw new ErrorException("Unexpected error", context);
        }
        //handle errors defined at the API level
        validateResponse(response, context);

        //extract result from the http response
        String responseBody = ((HttpStringResponse) response).getBody();
        Profile result = ApiHelper.deserialize(responseBody,
                Profile.class);

        return result;
    }

    /**
     * The User Activity endpoint returns data about a user's lifetime activity with Uber. The
     * response will include pickup locations and times, dropoff locations and times, the distance
     * of past requests, and information about which products were requested.<br><br>The history
     * array in the response will have a maximum length based on the limit parameter. The response
     * value count may exceed limit, therefore subsequent API requests may be necessary.
     * @param  offset  Optional parameter: Offset the list of returned results by this amount.
     *         Default is zero.
     * @param  limit  Optional parameter: Number of items to retrieve. Default is 5, maximum is 100.
     * @return    Returns the Activities response from the API call
     * @throws    ApiException    Represents error response from the server.
     * @throws    IOException    Signals that an I/O exception of some sort has occurred.
     */
    public Activities userActivity(
            final Integer offset,
            final Integer limit) throws ApiException, IOException {
        HttpRequest request = buildUserActivityRequest(offset, limit);
        HttpResponse response = getClientInstance().execute(request, false);
        HttpContext context = new HttpContext(request, response);

        return handleUserActivityResponse(context);
    }

    /**
     * The User Activity endpoint returns data about a user's lifetime activity with Uber. The
     * response will include pickup locations and times, dropoff locations and times, the distance
     * of past requests, and information about which products were requested.<br><br>The history
     * array in the response will have a maximum length based on the limit parameter. The response
     * value count may exceed limit, therefore subsequent API requests may be necessary.
     * @param  offset  Optional parameter: Offset the list of returned results by this amount.
     *         Default is zero.
     * @param  limit  Optional parameter: Number of items to retrieve. Default is 5, maximum is 100.
     * @return    Returns the Activities response from the API call
     */
    public CompletableFuture<Activities> userActivityAsync(
            final Integer offset,
            final Integer limit) {
        return makeHttpCallAsync(() -> buildUserActivityRequest(offset, limit),
            request -> getClientInstance().executeAsync(request, false),
            context -> handleUserActivityResponse(context));
    }

    /**
     * Builds the HttpRequest object for userActivity.
     */
    private HttpRequest buildUserActivityRequest(
            final Integer offset,
            final Integer limit) {
        //the base uri for api requests
        String baseUri = config.getBaseUri();

        //prepare query string for API call
        final StringBuilder queryBuilder = new StringBuilder(baseUri
                + "/history");

        //load all query parameters
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("offset", offset);
        queryParameters.put("limit", limit);

        //load all headers for the outgoing API request
        Headers headers = new Headers();
        headers.add("user-agent", BaseController.userAgent);
        headers.add("accept", "application/json");

        //prepare and invoke the API call request to fetch the response
        HttpRequest request = getClientInstance().get(queryBuilder, headers, queryParameters,
                null);

        // Invoke the callback before request if its not null
        if (getHttpCallback() != null) {
            getHttpCallback().onBeforeRequest(request);
        }

        return request;
    }

    /**
     * Processes the response for userActivity.
     * @return An object of type Activities
     */
    private Activities handleUserActivityResponse(
            HttpContext context) throws ApiException, IOException {
        HttpResponse response = context.getResponse();

        //invoke the callback after response if its not null
        if (getHttpCallback() != null) {
            getHttpCallback().onAfterResponse(context);
        }

        //Error handling using HTTP status codes
        int responseCode = response.getStatusCode();

        if ((responseCode < 200) || (responseCode > 208)) {
            throw new ErrorException("Unexpected error", context);
        }
        //handle errors defined at the API level
        validateResponse(response, context);

        //extract result from the http response
        String responseBody = ((HttpStringResponse) response).getBody();
        Activities result = ApiHelper.deserialize(responseBody,
                Activities.class);

        return result;
    }

}