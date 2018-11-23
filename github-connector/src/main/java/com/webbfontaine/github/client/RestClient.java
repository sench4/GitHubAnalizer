package com.webbfontaine.github.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Abstraction for any rest client
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public abstract class RestClient {

    private RestTemplate restTemplate;

    public RestClient(final RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    /**
     * Makes HTTP GET call to the API. Makes absolute url call by using relative parameter path and method {@link RestClient#buildUrl(String)}
     * @param path The relative path to the resource
     * @param responseType The response type
     * @param uriVariables The url parameters sequence
     * @param <T> The response type
     * @return
     * @throws RestClientException
     */
    public <T> ResponseEntity<T> getForEntity(String path, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.getForEntity(buildUrl(path), responseType, uriVariables);
    }

    /**
     * Builds REST API core url for a given client
     * @param path
     * @return
     */
    public abstract String buildUrl(final String path);
}
