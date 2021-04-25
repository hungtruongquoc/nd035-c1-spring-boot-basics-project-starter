package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(File file) {
        return this.fileMapper.insert(file);
    }

    public File[] getAllFiles() {
        return this.fileMapper.files();
    }

    public File findById(Integer id) {
        return this.fileMapper.findById(id);
    }

    public Integer delete(Integer id) {
        return this.fileMapper.delete(id);
    }

    public File getFile(Integer fileId, Integer userId) {
        return fileMapper.getFile(fileId, userId);
    }
}
