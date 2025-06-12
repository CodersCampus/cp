package com.coderscampus.cp.domain;

import jakarta.persistence.*;

@Entity(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the profile

    @Column(unique = true, nullable = false, length = 60)
    private String username; // Unique username for display purposes

    @Column(nullable = true, length = 60)
    private String displayName; // Optional display name for the user

    @Column(nullable = true, length = 60)
    private String firstName;

    @Column(nullable = true, length = 60)
    private String lastName;

    @Column(nullable = true, length = 255)
    private String pictureUrl;

    @Column(nullable = true, length = 500)
    private String bio;

    @Column(nullable = true, length = 255)
    private String websiteUrl;

    @Column(nullable = true, length = 255)
    private String linkedInUrl;

    @Column(nullable = true, length = 255)
    private String githubUrl;

    @Column(nullable = true, length = 15)
    private String phoneNumber;

    @Column(nullable = true, length = 255)
    private String address;

    @Column(nullable = true, length = 100)
    private String city;

    @Column(nullable = true, length = 100)
    private String state;

    @Column(nullable = true, length = 20)
    private String zipCode;

    @Column(nullable = true, length = 100)
    private String country;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user; // One-to-one relationship with User

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profile [" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", bio='" + bio + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", linkedInUrl='" + linkedInUrl + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ']';
    }
}
