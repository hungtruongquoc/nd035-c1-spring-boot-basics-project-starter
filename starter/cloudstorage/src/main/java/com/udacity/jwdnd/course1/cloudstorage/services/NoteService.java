package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note newNote) {
        return noteMapper.insert(newNote);
    }

    public Note[] getAll() {
        return noteMapper.getAll();
    }

    public Note[] getAllByUser(Integer userId) {
        return noteMapper.getAllByUser(userId);
    }

    public Note getById(Integer noteId) {
        return noteMapper.findById(noteId);
    }

    public int updateNote(Note currentNote) {
        return noteMapper.update(currentNote);
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.delete(noteId);
    }
}
