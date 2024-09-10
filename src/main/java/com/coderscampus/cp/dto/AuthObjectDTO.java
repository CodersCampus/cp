package com.coderscampus.cp.dto;

public class AuthObjectDTO {
    private String uid;
    private String displayName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }



    @Override
    public String toString() {
        return "AuthObjectDto{" +
                "uid='" + uid + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }

}
