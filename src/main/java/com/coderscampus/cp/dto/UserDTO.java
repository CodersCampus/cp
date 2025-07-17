package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.User;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String displayName;
    private String photoUrl;
    private String password;
    private Boolean active;
    private Boolean online;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.photoUrl = user.getPhotoUrl();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.online = user.getOnline();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", online=" + online +
                '}';
    }
}
