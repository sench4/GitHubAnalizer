package com.webbfontaine.githubanalizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Arsen Aleksanyan on 11/20/18.
 */
@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

    public static final String WEB_RESOURCES_PATH_NAME = "web-resources";
    public static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"/WEB-INF/classes/","classpath:/BOOT-INF/classes/","classpath:/"};

    @Autowired private RestAppConfig restAppConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(WEB_RESOURCES_PATH_NAME + "/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(restAppConfig.jsonConverter());
    }
}
