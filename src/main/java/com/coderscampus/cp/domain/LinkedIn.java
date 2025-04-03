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
	private String email;
	private String firstName;
	private String lastName;
	private String biography;
	private String education;
	private String experience;
	private String location;
	private String image;
	private String title;

	public LinkedIn(Long id, String decoratedHeader, String collapsedBio, String url, String featuredPosts,
			String activity, String skills, String email, String firstName, String lastName, String biography,
			String education, String experience, String location, String image, String title) {
		super();
		this.id = id;
		this.decoratedHeader = decoratedHeader;
		this.collapsedBio = collapsedBio;
		this.url = url;
		this.featuredPosts = featuredPosts;
		this.activity = activity;
		this.skills = skills;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
		this.education = education;
		this.experience = experience;
		this.location = location;
		this.image = image;
		this.title = title;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "LinkedIn [id=" + id + ", decoratedHeader=" + decoratedHeader + ", collapsedBio=" + collapsedBio
				+ ", url=" + url + ", featuredPosts=" + featuredPosts + ", activity=" + activity + ", skills=" + skills
				+ ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", biography="
				+ biography + ", education=" + education + ", experience=" + experience + ", location=" + location
				+ ", image=" + image + ", title=" + title + "]";
	}

}
