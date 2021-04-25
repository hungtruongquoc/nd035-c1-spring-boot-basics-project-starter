package com.udacity.jwdnd.course1.cloudstorage.exceptions;

public class FileExistenceException extends RuntimeException {
    public FileExistenceException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
