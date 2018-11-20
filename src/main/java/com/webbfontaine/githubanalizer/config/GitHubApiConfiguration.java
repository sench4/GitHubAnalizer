package com.webbfontaine.githubanalizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arsen Aleksanyan on 11/20/18.
 */
@Configuration
public class GitHubApiConfiguration {

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Autowired
    private RestAppConfig restAppConfig;

    @Bean
    public RestTemplate gitHubRestTemplate(){
        RestTemplate gitHubRestTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new LinkedList<>();
        messageConverters.addAll(gitHubRestTemplate.getMessageConverters());
        messageConverters.add(restAppConfig.jsonConverter());
        gitHubRestTemplate.setMessageConverters(messageConverters);

        return gitHubRestTemplate;
    }

    public String getGithubApiUrl() { return githubApiUrl; }
}
