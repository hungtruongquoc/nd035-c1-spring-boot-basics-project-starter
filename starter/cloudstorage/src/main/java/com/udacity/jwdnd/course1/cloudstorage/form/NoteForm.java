package com.udacity.jwdnd.course1.cloudstorage.form;

public class NoteForm {
    private String noteTitle;
    private String noteDescription;
    private Integer noteId;

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return this.noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return this.noteId;
    }

    public NoteForm setNoteId(Integer id) {
        this.noteId = id;
        return this;
    }

    public String getContent() {
        return String.format("%s %s", getNoteTitle(), getNoteDescription());
    }

    public boolean isValid() {
        if (null == this.noteTitle || "".equals(this.noteTitle) || this.noteTitle.length() > 20) {
            return false;
        }
        return null != this.noteDescription && !"".equals(this.noteDescription) && this.noteDescription.length() <= 1000;
    }

    public boolean hasNoteId() {
        return null != noteId;
    }
}
