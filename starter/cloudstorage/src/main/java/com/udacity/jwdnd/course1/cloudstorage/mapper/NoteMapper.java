package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    Note[] getAll();

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note findById(Integer noteId);

    @Update("UPDATE NOTES SET noteTitle=#{noteTitle}, noteDescription=#{noteDescription} WHERE noteId=#{noteId}")
    int update(Note note);
}
