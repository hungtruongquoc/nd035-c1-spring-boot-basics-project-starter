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
        return this.noteMapper.insert(newNote);
    }

    public Note[] getAll() {
        return this.noteMapper.getAll();
    }

    public Note getById(Integer noteId) {
        return this.noteMapper.findById(noteId);
    }

    public int updateNote(Note currentNote) {
        return this.noteMapper.update(currentNote);
    }

    public int deleteNote(Integer noteId) {
        return this.noteMapper.delete(noteId);
    }
}
