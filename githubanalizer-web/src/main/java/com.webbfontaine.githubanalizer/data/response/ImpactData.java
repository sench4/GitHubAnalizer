package com.webbfontaine.githubanalizer.data.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * Created by Arsen Aleksanyan on 11/22/18.
 */
@JsonRootName("impact")
public class ImpactData implements Comparable<ImpactData>, Serializable {

    private CommiterData commiter;
    private Integer impact = 0;

    protected ImpactData(){}

    public ImpactData(CommiterData commiter) {
        this.commiter = commiter;
        addImpact();
    }

    public CommiterData getCommiter() { return commiter; }

    public Integer getImpact() { return impact; }

    @JsonIgnore
    public void addImpact(){ impact ++; }

    @Override
    public int compareTo(final ImpactData o) {
        return o.getImpact().compareTo(this.getImpact()); //Sorting DESC order
    }
}
