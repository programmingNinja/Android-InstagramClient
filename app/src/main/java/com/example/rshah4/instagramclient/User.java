package com.example.rshah4.instagramclient;

/**
 * Created by rshah4 on 10/9/15.
 */
public class User {
    String profileImageURL;
    String username;

    User() {}

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
