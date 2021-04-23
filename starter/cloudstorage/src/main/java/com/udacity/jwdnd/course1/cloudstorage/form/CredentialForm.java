package com.udacity.jwdnd.course1.cloudstorage.form;

public class CredentialForm {
    private String url;
    private String username;
    private String password;
    private Integer credentialId;
    private Integer userId;
    private String rawPassword;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String value) {
        this.url = value;
    }

    public String getUsername() {
        return this.username;
    }

    public CredentialForm setUsername(String value) {
        this.username = value;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String value) {
        this.rawPassword = value;
    }

    public boolean isValid() {
        if (null == this.url || "".equals(this.url)) {
            return false;
        }
        if (!this.url.contains("http://") && !this.url.contains("https://")) {
            return false;
        }
        if (!this.url.contains(".")) {
            return false;
        }
        if (null == this.username || "".equals(this.username)) {
            return false;
        }
        return (null != this.password && !"".equals(this.password)) || (null != this.rawPassword && !"".equals(this.rawPassword));
    }

    public String toString() {
        return String.format("url=%s, username=%s, password=%s, credentialId=%d, key=%s",
                this.url, this.username, this.password, this.credentialId, this.key);
    }

    public Integer getUserId() {
        return this.userId;
    }

    public CredentialForm setUserId(Integer value) {
        this.userId = value;
        return this;
    }

    public Integer getCredentialId() {
        return this.credentialId;
    }

    public CredentialForm setCredentialId(Integer value) {
        this.credentialId = value;
        return this;
    }

    public boolean hasId() {
        return null != this.credentialId;
    }
}
