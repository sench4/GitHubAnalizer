package com.webbfontaine.githubanalizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by Arsen Aleksanyan on 11/20/18.
 */
@SpringBootApplication
public class GitHubAnalizerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GitHubAnalizerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GitHubAnalizerApplication.class, args);
    }
}
