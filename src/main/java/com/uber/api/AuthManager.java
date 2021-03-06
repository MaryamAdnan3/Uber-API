/*
 * UberAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */

package com.uber.api;

import com.uber.api.exceptions.ApiException;
import com.uber.api.http.request.HttpRequest;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining the behavior of Authentication Classes.
 */
public interface AuthManager {

    /**
     * Adds authentication to the given HttpRequest.
     * @param httpRequest   HttpRequest object for authentication
     * @return Returns the object of HttpRequest
     * @throws ApiException   Represents error response from the server.
     * @throws IOException   Signals that an I/O exception of some sort has occurred.
     */
    HttpRequest apply(HttpRequest httpRequest) throws ApiException, IOException;

    /**
     * Asynchronously adds authentication to the given HttpRequest.
     * @param httpRequest   HttpRequest object for authentication
     * @return Returns the completable future of HttpRequest
     */
    CompletableFuture<HttpRequest> applyAsync(HttpRequest httpRequest);
}
