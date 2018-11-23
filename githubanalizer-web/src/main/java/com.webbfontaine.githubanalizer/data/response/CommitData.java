package com.webbfontaine.githubanalizer.data.response;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
@JsonRootName("commit")
public class CommitData {

    private CommiterData commiter;
    private String message;

    protected CommitData(){}


    public CommitData(CommiterData commiter, String message) {
        this.commiter = commiter;
        this.message = message;
    }

    public CommiterData getCommiter() { return commiter; }

    public String getMessage() { return message; }
}
