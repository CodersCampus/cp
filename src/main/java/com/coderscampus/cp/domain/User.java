package com.coderscampus.cp.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    private String uid; // Unique identifier for the user, can be used for OAuth
    private String username; // Unique username for the user, can be used for login
    private String displayName; // Unique displayName for display purposes
    private String email;
    private String password;
    private Boolean enabled;
    private Boolean online;
    private Instant createdAt;
    private Instant updatedAt;
    private String provider; // OAuth provider (e.g., "google", "github", etc.)
    private String providerId; // Unique ID from the OAuth provider

    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private GitHub githubHandle;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private LinkedIn linkedIn;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private YouTube youtube;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private FinalProject finalProject;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private Resume resume;
    //    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    //	private Foobar foobar;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private Networking networking;
    //	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //	private Website website;

    public User() {
        this.enabled = true; // Default to enabled
        this.createdAt = Instant.now(); // Set creation time to now
    }

    public User(String uid, String username, String displayName, String email, String password, Boolean enabled, Boolean online, Instant createdAt, Instant updatedAt, String provider, String providerId) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    //	public GitHub getGithubHandle() {
//		return githubHandle;
//	}
//
//	public void setGithubHandle(GitHub githubHandle) {
//		this.githubHandle = githubHandle;
//	}
//
//	public LinkedIn getLinkedIn() {
//		return linkedIn;
//	}
//
//	public void setLinkedIn(LinkedIn linkedIn) {
//		this.linkedIn = linkedIn;
//	}
//
//
//	public YouTube getYoutube() {
//		return youtube;
//	}
//
//	public void setYoutube(YouTube youtube) {
//		this.youtube = youtube;
//	}
//
//	public FinalProject getFinalProject() {
//		return finalProject;
//	}
//
//	public void setFinalProject(FinalProject finalProject) {
//		this.finalProject = finalProject;
//	}
//
//	public Resume getResume() {
//		return resume;
//	}
//
//	public void setResume(Resume resume) {
//		this.resume = resume;
//	}
//
//	public Foobar getFoobar() {
//		return foobar;
//	}
//
//	public void setFoobar(Foobar foobar) {
//		this.foobar = foobar;
//	}
//
//	public Networking getNetworking() {
//		return networking;
//	}
//
//	public void setNetworking(Networking networking) {
//		this.networking = networking;
//	}
//
//	public Website getWebsite() {
//		return website;
//	}
//
//	public void setWebsite(Website website) {
//		this.website = website;
//	}


    @Override
    public String toString() {
        return "User [" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
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