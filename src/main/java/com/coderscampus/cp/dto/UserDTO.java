package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.User;

public class UserDTO {
    private Long id;
    private String email;
    private String displayName;
    private String password;

    public UserDTO() {
        this(new User());
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' + ", password='" + password + '\'' + ']';
    }
}
