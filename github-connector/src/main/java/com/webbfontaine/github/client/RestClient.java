package com.webbfontaine.github.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public abstract class RestClient {

    private RestTemplate restTemplate;

    public RestClient(final RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> getForEntity(String path, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return restTemplate.getForEntity(buildUrl(path), responseType, uriVariables);
    }

    public abstract String buildUrl(final String path);
}
