package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    private static final Logger logger = LoggerFactory.getLogger(CredentialService.class);

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public Credential[] getAll() {
        Credential[] records = this.credentialMapper.getAll();
        logger.info("Get all credentials in service: {}", (Object[]) records);
        return records;
    }

    public Credential[] getCredentialsByUser(Integer userId) {
        Credential[] records = this.credentialMapper.getByUserId(userId);
        logger.info("Get all credentials in service by user: {}", (Object[]) records);
        return records;
    }

    public int insert(Credential newCredential) {
        return this.credentialMapper.insert(newCredential);
    }

    public Credential getById(Integer credentialId) {
        return this.credentialMapper.findById(credentialId);
    }
}
