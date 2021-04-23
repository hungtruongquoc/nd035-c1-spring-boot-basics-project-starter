package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    private final EncryptionService encryptor;

    private static final Logger logger = LoggerFactory.getLogger(CredentialService.class);

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptor) {
        this.credentialMapper = credentialMapper;
        this.encryptor = encryptor;
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
        encryptPassword(newCredential);
        return this.credentialMapper.insert(newCredential);
    }

    public int update(Credential credential) {
        encryptPassword(credential);
        return this.credentialMapper.update(credential);
    }

    public Credential getById(Integer credentialId) {
        Credential currentCredential = this.credentialMapper.findById(credentialId);
        currentCredential.setRawPassword(encryptor.decryptValue(currentCredential.getPassword(), currentCredential.getKey()));
        return currentCredential;
    }

    public Integer deleteCredential(Integer credentialId) {
        return this.credentialMapper.delete(credentialId);
    }

    private void encryptPassword(Credential credential) {
        credential.setPassword(encryptor.encryptValue(credential.getPassword(), credential.getKey()));
    }
}
