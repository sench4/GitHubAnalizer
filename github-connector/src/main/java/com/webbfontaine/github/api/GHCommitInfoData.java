package com.webbfontaine.github.api;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/21/18.
 */
public class GHCommitInfoData implements Serializable {

    private GHCommitData commit;
    private GHAuthorData author;

    protected GHCommitInfoData(){}

    public GHCommitData getCommit() { return commit; }

    public GHAuthorData getAuthor() { return author; }
}
