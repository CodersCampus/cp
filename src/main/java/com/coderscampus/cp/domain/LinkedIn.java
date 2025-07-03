package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity
public class LinkedIn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
	private Boolean banner;
	private Boolean about;
	private String url;
	private Boolean featuredPosts;
	private Boolean activity;
	private Boolean skills;
	private Boolean email;
	private String firstName;
	private String lastName;
	private Boolean biography;
	private Boolean education;
	private Boolean experience;
	private String location;
	private Boolean image;
	private Boolean title;

    public LinkedIn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getBanner() {
        return banner;
    }

    public void setBanner(Boolean banner) {
        this.banner = banner;
    }

    public Boolean getAbout() {
        return about;
    }

    public void setAbout(Boolean about) {
        this.about = about;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getFeaturedPosts() {
        return featuredPosts;
    }

    public void setFeaturedPosts(Boolean featuredPosts) {
        this.featuredPosts = featuredPosts;
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public Boolean getSkills() {
        return skills;
    }

    public void setSkills(Boolean skills) {
        this.skills = skills;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
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

    public Boolean getBiography() {
        return biography;
    }

    public void setBiography(Boolean biography) {
        this.biography = biography;
    }

    public Boolean getEducation() {
        return education;
    }

    public void setEducation(Boolean education) {
        this.education = education;
    }

    public Boolean getExperience() {
        return experience;
    }

    public void setExperience(Boolean experience) {
        this.experience = experience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public Boolean getTitle() {
        return title;
    }

    public void setTitle(Boolean title) {
        this.title = title;
    }
}
