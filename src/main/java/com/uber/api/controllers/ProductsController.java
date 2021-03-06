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
import com.uber.api.models.ProductList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * This class lists all the endpoints of the groups.
 */
public final class ProductsController extends BaseController {

    /**
     * Initializes the controller.
     * @param config    Configurations added in client.
     * @param httpClient    Send HTTP requests and read the responses.
     * @param authManagers    Apply authorization to requests.
     */
    public ProductsController(Configuration config, HttpClient httpClient,
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
    public ProductsController(Configuration config, HttpClient httpClient,
            Map<String, AuthManager> authManagers, HttpCallback httpCallback) {
        super(config, httpClient, authManagers, httpCallback);
    }

    /**
     * The Products endpoint returns information about the Uber products offered at a given
     * location. The response includes the display name and other details about each product, and
     * lists the products in the proper display order.
     * @param  latitude  Required parameter: Latitude component of location.
     * @param  longitude  Required parameter: Longitude component of location.
     * @return    Returns the ProductList response from the API call
     * @throws    ApiException    Represents error response from the server.
     * @throws    IOException    Signals that an I/O exception of some sort has occurred.
     */
    public ProductList productTypes(
            final double latitude,
            final double longitude) throws ApiException, IOException {
        HttpRequest request = buildProductTypesRequest(latitude, longitude);
        HttpResponse response = getClientInstance().execute(request, false);
        HttpContext context = new HttpContext(request, response);

        return handleProductTypesResponse(context);
    }

    /**
     * The Products endpoint returns information about the Uber products offered at a given
     * location. The response includes the display name and other details about each product, and
     * lists the products in the proper display order.
     * @param  latitude  Required parameter: Latitude component of location.
     * @param  longitude  Required parameter: Longitude component of location.
     * @return    Returns the ProductList response from the API call
     */
    public CompletableFuture<ProductList> productTypesAsync(
            final double latitude,
            final double longitude) {
        return makeHttpCallAsync(() -> buildProductTypesRequest(latitude, longitude),
            request -> getClientInstance().executeAsync(request, false),
            context -> handleProductTypesResponse(context));
    }

    /**
     * Builds the HttpRequest object for productTypes.
     */
    private HttpRequest buildProductTypesRequest(
            final double latitude,
            final double longitude) {
        //the base uri for api requests
        String baseUri = config.getBaseUri();

        //prepare query string for API call
        final StringBuilder queryBuilder = new StringBuilder(baseUri
                + "/products");

        //load all query parameters
        Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put("latitude", latitude);
        queryParameters.put("longitude", longitude);

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
     * Processes the response for productTypes.
     * @return An object of type ProductList
     */
    private ProductList handleProductTypesResponse(
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
        ProductList result = ApiHelper.deserialize(responseBody,
                ProductList.class);

        return result;
    }

}