package com.webbfontaine.github.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
@JsonRootName("repoSearchResultData")
public class GHRepoSearchResultData implements Serializable {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("items")
    private GHRepositoryDataList items;

    protected GHRepoSearchResultData(){}

    public GHRepositoryDataList getItems() { return items; }

    public Integer getTotalCount() { return totalCount; }
}
