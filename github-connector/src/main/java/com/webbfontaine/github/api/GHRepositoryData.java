package com.webbfontaine.github.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public class GHRepositoryData implements Serializable {

    private String id;
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private GHRepoOwnerData owner;

    protected GHRepositoryData(){}

    public String getId() { return id; }

    public String getName() { return name; }

    public String getFullName() { return fullName; }

    public GHRepoOwnerData getOwner() { return owner; }
}
