package com.webbfontaine.github.api;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public class GHRepoOwnerData implements Serializable {

    private String login;

    protected GHRepoOwnerData(){}

    public String getLogin() { return login; }
}
