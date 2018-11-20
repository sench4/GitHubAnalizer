package com.webbfontaine.githubanalizer.client;

import com.webbfontaine.githubanalizer.config.GitHubApiConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 * Created by Arsen Aleksanyan on 11/20/18.
 */
@Service
public class GitHubClient {

    @Autowired private GitHubApiConfiguration ghApiConfig;



    private String buildGitHubUrl(final String path){
        return ghApiConfig.getGithubApiUrl() + "/" + path;
    }
}
