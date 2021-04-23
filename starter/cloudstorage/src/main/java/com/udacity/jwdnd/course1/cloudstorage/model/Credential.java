package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;

    public Integer getCredentialId() {
        return credentialId;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public Credential setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
        return this;
    }

    public Credential setUrl(String url) {
        this.url = url;
        return this;
    }

    public Credential setUsername(String username) {
        this.username = username;
        return this;
    }

    public Credential setKey(String key) {
        this.key = key;
        return this;
    }

    public Credential setPassword(String password) {
        this.password = password;
        return this;
    }

    public Credential setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }
}
