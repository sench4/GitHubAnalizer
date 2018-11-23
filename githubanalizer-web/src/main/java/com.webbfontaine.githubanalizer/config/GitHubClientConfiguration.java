package com.webbfontaine.githubanalizer.config;

import com.webbfontaine.github.client.GitHubClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arsen Aleksanyan on 11/23/18.
 */
@Configuration
public class GitHubClientConfiguration {

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Value("${github.username}")
    private String ghUserName;

    @Value("${github.password}")
    private String ghPassword;

    @Bean
    public RestTemplate gitHubRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
        messageConverters.addAll(restTemplate.getMessageConverters());
        messageConverters.add(gitHubJsonConverter());
        restTemplate.setMessageConverters(messageConverters);

        if(ghUserName != null && ghPassword != null) {
            List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
            interceptors.add(new BasicAuthenticationInterceptor(ghUserName, ghPassword));
        }

        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter gitHubJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json;charset=UTF-8"));
        return converter;
    }

    @Bean
    public GitHubClient gitHubClient(){
        return new GitHubClient(gitHubRestTemplate(), githubApiUrl);
    }
}
