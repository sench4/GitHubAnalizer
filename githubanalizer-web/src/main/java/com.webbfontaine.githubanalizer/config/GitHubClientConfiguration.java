package com.webbfontaine.githubanalizer.config;

import com.webbfontaine.github.client.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Arsen Aleksanyan on 11/23/18.
 */
@Configuration
public class GitHubClientConfiguration {

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Bean
    public GitHubClient gitHubClient(){
        return new GitHubClient(restTemplate, githubApiUrl);
    }
}
