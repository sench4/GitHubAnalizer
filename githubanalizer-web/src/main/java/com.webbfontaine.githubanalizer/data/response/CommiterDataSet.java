package com.webbfontaine.githubanalizer.data.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.LinkedHashSet;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
@JsonRootName("commiters")
public class CommiterDataSet extends LinkedHashSet<CommiterData> {
}
