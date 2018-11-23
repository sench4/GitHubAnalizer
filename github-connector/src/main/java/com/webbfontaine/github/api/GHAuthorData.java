package com.webbfontaine.github.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
public class GHAuthorData implements Serializable {

    private String login;
    private String url;
    @JsonProperty("avatar_url")
    private String avatarUrl;

    protected GHAuthorData(){}

    public String getLogin() { return login; }

    public String getUrl() { return url; }

    public String getAvatarUrl() { return avatarUrl; }
}
