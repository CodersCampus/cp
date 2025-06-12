package com.coderscampus.cp.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; // Unique username for display purposes
    private String email;
    private String password;
    public enum Role { USER, ADMIN, MODERATOR }
    private Boolean enabled;
    private Boolean online;
    private Instant createdAt;
    private Instant updatedAt;
    private String provider; // OAuth provider (e.g., "google", "github", etc.)
    private String providerId; // Unique ID from the OAuth provider

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile; // One-to-one relationship with Profile

    public User() {
        this.enabled = true; // Default to enabled
        this.createdAt = Instant.now(); // Set creation time to now
    }

    public User(String username, String email, String password, Boolean enabled, Boolean online, Instant createdAt, Instant updatedAt, String provider, String providerId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.online = online;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.provider = provider;
        this.providerId = providerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User [" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", online=" + online +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", provider='" + provider + '\'' +
                ", providerId='" + providerId + '\'' +
                ", profile=" + profile +
                ']';
    }
}