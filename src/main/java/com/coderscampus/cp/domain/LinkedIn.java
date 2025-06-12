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
	private String banner; // URL or String representation of the banner image - Boolean
	private String about; // A brief description or summary of the profile - Boolean
	private String url; // URL of the LinkedIn profile - Boolean
	private String featuredPosts; // A list of featured posts (String representation, could be comma-separated) - Boolean
	private String activity; // A list of recent activities (String representation, could be comma-separated) - Boolean
	private String skills; // A list of skills (String representation, could be comma-separated) - Boolean
	private String email; // Email address associated with the LinkedIn profile - Boolean
	private String firstName;
	private String lastName;
	private String biography; // A brief biography or personal statement - Boolean
	private String education; // A list of educational qualifications (String representation, could be comma-separated) - Boolean
	private String experience; // A list of work experiences (String representation, could be comma-separated) - Boolean
	private String location;
	private String image; // URL or String representation of the profile image - Boolean
	private String title; // The current job title or position - Boolean

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

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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
}
