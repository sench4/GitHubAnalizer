package com.webbfontaine.github.api;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public class GHCommitAuthorData implements Serializable {

    private String name;
    private String email;
    private LocalDateTime date;

    protected GHCommitAuthorData(){}

    public String getName() { return name; }

    public String getEmail() { return email; }

    public LocalDateTime getDate() { return date; }
}
