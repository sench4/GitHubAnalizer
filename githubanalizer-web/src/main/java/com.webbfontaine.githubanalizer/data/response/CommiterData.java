package com.webbfontaine.githubanalizer.data.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.webbfontaine.github.api.GHCommitInfoData;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
@JsonRootName("commiter")
public class CommiterData implements Serializable {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private String name;
    private String email;
    private String commitDate;

    protected CommiterData(){}

    public CommiterData(String name, String email, LocalDateTime commitDate) {
        this.name = name;
        this.email = email;
        this.commitDate = dateTimeFormatter.format(commitDate);
    }

    public CommiterData(final GHCommitInfoData ci){
        this(ci.getCommit().getAuthor().getName(), ci.getCommit().getAuthor().getEmail(), ci.getCommit().getAuthor().getDate());
    }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getCommitDate() { return commitDate; }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof CommiterData)){
            return false;
        }

        return this.getEmail().equals(((CommiterData) obj).getEmail());
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
