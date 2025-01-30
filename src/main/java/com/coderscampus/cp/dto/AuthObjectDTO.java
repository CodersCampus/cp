package com.coderscampus.cp.dto;

public class AuthObjectDTO {
    private String uid;
    private String email;
    private String displayName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "AuthObjectDto [uid=" + uid + ", email=" + email + ", displayName=" + displayName + "]";
    }
}
