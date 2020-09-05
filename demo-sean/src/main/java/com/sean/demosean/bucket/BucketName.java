package com.sean.demosean.bucket;

public enum BucketName {
    PROFILE_IMAGE("sean-image-upload-1360851");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
