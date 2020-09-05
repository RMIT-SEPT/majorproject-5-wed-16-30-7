package com.sean.demosean.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {
    private UUID userProfileID;
    private String username;
    private String userImageLink; // this will be the s3 key

    public UserProfile(UUID userProfileID, String username, String userImageLink) {
        this.userProfileID = userProfileID;
        this.username = username;
        this.userImageLink = userImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(getUserProfileID(), that.getUserProfileID()) &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getUserImageLink(), that.getUserImageLink());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserProfileID(), getUsername(), getUserImageLink());
    }

    public UUID getUserProfileID() {
        return userProfileID;
    }

    public void setUserProfileID(UUID userProfileID) {
        this.userProfileID = userProfileID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getUserImageLink() {
        return Optional.ofNullable(userImageLink);
    }

    public void setUserImageLink(String userImageLink) {
        this.userImageLink = userImageLink;
    }
}
