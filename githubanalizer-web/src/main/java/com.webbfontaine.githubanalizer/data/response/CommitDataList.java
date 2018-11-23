package com.webbfontaine.githubanalizer.data.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.LinkedList;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
@JsonRootName("commits")
public class CommitDataList extends LinkedList<CommitData> {
}
