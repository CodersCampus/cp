package com.coderscampus.cp.dto;

public class AuthObjectDto {
    private String uid;
    private String displayName;
    //    DON NOT NEED FOR #512 TO WORK, BUT NICE TO HAVE
//    private String photoURL; // Add this field


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

//    DON NOT NEED FOR #512 TO WORK, BUT NICE TO HAVE
//    public String getPhotoURL() {
//        return photoURL;
//    }
//
//    public void setPhotoURL(String photoURL) {
//        this.photoURL = photoURL;
//    }

    @Override
    public String toString() {
        return "AuthObjectDto{" +
                "uid='" + uid + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }

//    DON NOT NEED FOR #512 TO WORK, BUT NICE TO HAVE
//    @Override
//    public String toString() {
//        return "AuthObjectDto{" +
//                "uid='" + uid + '\'' +
//                ", displayName='" + displayName + '\'' +
//                ", photoURL='" + photoURL + '\'' +
//                '}';
//    }
}
