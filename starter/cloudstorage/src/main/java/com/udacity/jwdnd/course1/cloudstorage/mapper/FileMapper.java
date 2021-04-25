package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findById(Integer fileId);

    @Select("SELECT * FROM FILES")
    File[] files();

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FILES WHERE fileId= #{id}")
    Integer delete(Integer id);

    @Select("SELECT * FROM FILES WHERE fileId=#{id} AND userId=#{userId}")
    File getFile(Integer id, Integer userId);
}
