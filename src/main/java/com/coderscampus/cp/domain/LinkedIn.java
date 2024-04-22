package com.coderscampus.cp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LinkedIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String decoratedHeader;
    private String collapsedBio;
    private String url;
    private String featuredPosts;
    private String activity;
    private String skills;
    public LinkedIn() {

    }

    public LinkedIn(Long id, String decoratedHeader, String collapsedBio, String url, String featuredPosts, String activity, String skills) {
        this.id = id;
        this.decoratedHeader = decoratedHeader;
        this.collapsedBio = collapsedBio;
        this.url = url;
        this.featuredPosts = featuredPosts;
        this.activity = activity;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDecoratedHeader() {
        return decoratedHeader;
    }

    public void setDecoratedHeader(String decoratedHeader) {
        this.decoratedHeader = decoratedHeader;
    }

    public String getCollapsedBio() {
        return collapsedBio;
    }

    public void setCollapsedBio(String collapsedBio) {
        this.collapsedBio = collapsedBio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFeaturedPosts() {
        return featuredPosts;
    }

    public void setFeaturedPosts(String featuredPosts) {
        this.featuredPosts = featuredPosts;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "LinkedIn{" +
                "id=" + id +
                ", decoratedHeader='" + decoratedHeader + '\'' +
                ", collapsedBio='" + collapsedBio + '\'' +
                ", url='" + url + '\'' +
                ", featuredPosts='" + featuredPosts + '\'' +
                ", activity='" + activity + '\'' +
                ", skills='" + skills + '\'' +
                '}';
    }
}
