package com.webbfontaine.githubanalizer.data.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
@JsonRootName("impacts")
public class ImpactDataList extends LinkedList<ImpactData> {

    public ImpactDataList(Collection<ImpactData> impacts){
        super(impacts);
    }
}
