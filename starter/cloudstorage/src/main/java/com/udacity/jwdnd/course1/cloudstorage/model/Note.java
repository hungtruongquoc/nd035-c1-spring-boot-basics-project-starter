package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Note setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Note setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Note setNoteId(Integer id) {
        this.noteId = id;
        return this;
    }

    public Integer getNoteId() {
        return this.noteId;
    }

    public String getContent() {
        return String.format("%d %s %s", getNoteId(), getNoteTitle(), getNoteDescription());
    }
}
