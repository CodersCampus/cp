package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.User;

public class UserDTO {
    private Long id;
    private String email;
    private String displayName;
    private Boolean enabled;
    private Boolean online;

    public UserDTO() {
        this(new User());
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
        this.enabled = user.getEnabled();
        this.online = user.getOnline();
    }

    public UserDTO(Long id, String email, String displayName) {

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", enabled=" + enabled +
                ", online=" + online +
                '}';
    }
}
