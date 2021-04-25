package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileExistenceException;
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
        if (checkFileNameExistence(file.getFileName(), file.getUserId())) {
            throw new FileExistenceException(file.getFileName() + " already exists.", null);
        }
        return this.fileMapper.insert(file);
    }

    public File[] getAllFiles() {
        return this.fileMapper.files();
    }

    public Boolean checkFileNameExistence(String fileName, Integer userId) {
        return fileMapper.getCountByName(fileName, userId) > 0;
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
