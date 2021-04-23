package com.udacity.jwdnd.course1.cloudstorage.model;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

public class Credential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;
    private String rawPassword;

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

    public String getFull() {
        return String.format("%d", credentialId);
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public Credential setRawPassword(String value) {
        rawPassword = value;
        return this;
    }

    public boolean hasRawPassword() {
        return null != getRawPassword() && !getRawPassword().equals("");
    }

    public boolean hasPassword() {
        return null != getPassword() && !getPassword().equals("");
    }

    public boolean hasId() {
        return null != getCredentialId();
    }

    public void importFormValue(CredentialForm form) {
        setUsername(form.getUsername()).setUrl(form.getUrl()).setUserId(form.getUserId()).setKey(form.getKey());
        setPassword(hasId() ? form.getRawPassword() : form.getPassword());
    }
}
