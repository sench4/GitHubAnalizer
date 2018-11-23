package com.webbfontaine.github.api;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public class GHCommitData implements Serializable {

    private GHCommitAuthorData author;
    private String message;

    protected GHCommitData(){}

    public GHCommitAuthorData getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
