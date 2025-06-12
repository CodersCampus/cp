package com.coderscampus.cp.dto;

import com.coderscampus.cp.domain.User;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDTO() {
        this(new User());
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
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

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", username='" + username + '\'' +
                ", email='" + email + '\'' + ", password='" + password + '\'' + ']';
    }
}
