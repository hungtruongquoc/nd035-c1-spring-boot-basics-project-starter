package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileId;
    private Integer fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private Blob fileData;
}
