package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS")
    Credential[] getAll();

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{id}")
    Credential findById(Integer id);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credential[] getByUserId(Integer userId);

    @Delete("DELETE CREDENTIALS WHERE credentialid = #{id}")
    Integer delete(Integer id);
}
