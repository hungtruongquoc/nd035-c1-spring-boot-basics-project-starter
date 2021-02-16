package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private Integer userId;
    private Byte[] fileData;

    public File(Integer fileId, String fileName, String contentType, Long fileSize, Integer userId) {
        this.userId = userId;
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }
}
