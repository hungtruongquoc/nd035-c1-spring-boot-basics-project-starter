package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private Integer userId;
    private byte[] fileData;

    public File(Integer fileId, String fileName, String contentType, Long fileSize, Integer userId, byte[] data) {
        this.userId = userId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = data;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer value) {
        fileId = value;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public Integer getUserId() {
        return userId;
    }
}
