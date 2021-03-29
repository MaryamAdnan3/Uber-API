/*
 * UberAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */

package com.uber.api;

import com.uber.api.controllers.EstimatesController;
import com.uber.api.controllers.ProductsController;
import com.uber.api.controllers.UserController;
import com.uber.api.http.client.HttpCallback;
import com.uber.api.http.client.HttpClient;
import com.uber.api.http.client.HttpClientConfiguration;
import com.uber.api.http.client.OkClient;
import com.uber.api.http.client.ReadonlyHttpClientConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 * Gateway class for the library.
 * This class acts as a factory for Controllers.
 * It holds the state of the SDK.
 */
public final class UberAPIClient implements Configuration {

    /**
     * Private store for controllers.
     */
    private ProductsController products;
    private EstimatesController estimates;
    private UserController user;

    /**
     * Current API environment.
     */
    private final Environment environment;

    /**
     * The HTTP Client instance to use for making HTTP requests.
     */
    private final HttpClient httpClient;

    /**
     * The timeout to use for making HTTP requests.
     */
    private final long timeout;

    /**
     * Http Client Configuration instance.
     */
    private final ReadonlyHttpClientConfiguration httpClientConfig;

    /**
     * Map of authentication Managers.
     */
    private Map<String, AuthManager> authManagers;

    /**
     * Callback to be called before and after the HTTP call for an endpoint is made.
     */
    private final HttpCallback httpCallback;

    private UberAPIClient(Environment environment, HttpClient httpClient, long timeout,
            ReadonlyHttpClientConfiguration httpClientConfig, Map<String, AuthManager> authManagers,
            HttpCallback httpCallback) {
        this.environment = environment;
        this.httpClient = httpClient;
        this.timeout = timeout;
        this.httpClientConfig = httpClientConfig;
        this.httpCallback = httpCallback;

        this.authManagers = (authManagers == null) ? new HashMap<>() : new HashMap<>(authManagers);


        products = new ProductsController(this, this.httpClient, this.authManagers,
                this.httpCallback);
        estimates = new EstimatesController(this, this.httpClient, this.authManagers,
                this.httpCallback);
        user = new UserController(this, this.httpClient, this.authManagers, this.httpCallback);
    }

    /**
     * Shutdown the underlying HttpClient instance.
     */
    public static void shutdown() {
        OkClient.shutdown();
    }

    /**
     * Get the instance of ProductsController.
     * @return products
     */
    public ProductsController getProductsController() {
        return products;
    }

    /**
     * Get the instance of EstimatesController.
     * @return estimates
     */
    public EstimatesController getEstimatesController() {
        return estimates;
    }

    /**
     * Get the instance of UserController.
     * @return user
     */
    public UserController getUserController() {
        return user;
    }

    /**
     * Current API environment.
     * @return environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * The HTTP Client instance to use for making HTTP requests.
     * @return httpClient
     */
    private HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * The timeout to use for making HTTP requests.
     * @return timeout
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * Http Client Configuration instance.
     * @return httpClientConfig
     */
    public ReadonlyHttpClientConfiguration getHttpClientConfig() {
        return httpClientConfig;
    }

    /**
     * Get base URI by current environment.
     * @param server Server for which to get the base URI
     * @return Processed base URI
     */
    public String getBaseUri(Server server) {
        return environmentMapper(environment, server);
    }

    /**
     * Get base URI by current environment.
     * @return Processed base URI
     */
    public String getBaseUri() {
        return getBaseUri(Server.ENUM_DEFAULT);
    }

    /**
     * Base URLs by environment and server aliases.
     * @param environment Environment for which to get the base URI
     * @param server Server for which to get the base URI
     * @return base URL
     */
    private static String environmentMapper(Environment environment, Server server) {
        if (environment.equals(Environment.PRODUCTION)) {
            if (server.equals(Server.ENUM_DEFAULT)) {
                return "https://api.uber.com/v1";
            }
        }
        return "https://api.uber.com/v1";
    }

    /**
     * Converts this UberAPIClient into string format.
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return "UberAPIClient [" + "environment=" + environment + ", httpClientConfig="
                + httpClientConfig + ", authManagers=" + authManagers + "]";
    }

    /**
     * Builds a new {@link UberAPIClient.Builder} object.
     * Creates the instance with the state of the current client.
     * @return a new {@link UberAPIClient.Builder} object
     */
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.environment = getEnvironment();
        builder.httpClient = getHttpClient();
        builder.timeout = getTimeout();
        builder.authManagers = authManagers;
        builder.httpCallback = httpCallback;
        builder.setHttpClientConfig(httpClientConfig);
        return builder;
    }

    /**
     * Class to build instances of {@link UberAPIClient}.
     */
    public static class Builder {
        private Environment environment = Environment.PRODUCTION;
        private HttpClient httpClient;
        private long timeout = 0;
        private Map<String, AuthManager> authManagers = null;
        private HttpCallback httpCallback = null;
        private HttpClientConfiguration httpClientConfig;

        /**
         * Current API environment.
         * @param environment The environment for client.
         * @return Builder
         */
        public Builder environment(Environment environment) {
            this.environment = environment;
            return this;
        }

        /**
         * The timeout to use for making HTTP requests.
         * @param timeout must be greater then 0.
         * @return Builder
         */
        public Builder timeout(long timeout) {
            if (timeout > 0) {
                this.timeout = timeout;
            }
            return this;
        }

        /**
         * HttpCallback.
         * @param httpCallback Callback to be called before and after the HTTP call.
         * @return Builder
         */
        public Builder httpCallback(HttpCallback httpCallback) {
            this.httpCallback = httpCallback;
            return this;
        }

        private void setHttpClientConfig(ReadonlyHttpClientConfiguration httpClientConfig) {
            this.timeout = httpClientConfig.getTimeout();
        }

        /**
         * Builds a new UberAPIClient object using the set fields.
         * @return UberAPIClient
         */
        public UberAPIClient build() {
            httpClientConfig = new HttpClientConfiguration();
            httpClientConfig.setTimeout(timeout);
            httpClient = new OkClient(httpClientConfig);

            return new UberAPIClient(environment, httpClient, timeout, httpClientConfig,
                    authManagers, httpCallback);
        }
    }
}