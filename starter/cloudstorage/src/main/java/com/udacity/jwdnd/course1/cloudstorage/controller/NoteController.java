package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notes")
@EnableWebSecurity
public class NoteController {

    private final NoteService noteService;

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public ModelAndView createNote(Model model, @ModelAttribute("newNote") NoteForm note) {
        String createNoteError = null;
        String updateNoteError = null;
        boolean createNoteSuccess = false;
        boolean updateNoteSuccess = false;

        if (note.isValid()) {
            if (null == note.getNoteId()) {
                Note newNote = new Note();
                newNote.setNoteTitle(note.getNoteTitle()).setNoteDescription(note.getNoteDescription());

                if (1 == this.noteService.createNote(newNote)) {
                    createNoteSuccess = true;
                } else {
                    createNoteError = "Failed to create note. Please try again later.";
                }
            } else {
                Note currentNote = this.noteService.getById(note.getNoteId());
                currentNote.setNoteTitle(note.getNoteTitle());
                currentNote.setNoteDescription(note.getNoteDescription());
                if (1 == this.noteService.updateNote(currentNote)) {
                    updateNoteSuccess = true;
                } else {
                    updateNoteError = "Failed to update the note. Please try again later.";
                }
            }
        } else {
            createNoteError = "Input is invalid";
        }

        model.addAttribute("createNoteError", createNoteError);
        model.addAttribute("createNoteSuccess", createNoteSuccess);
        model.addAttribute("updateNoteError", updateNoteError);
        model.addAttribute("updateNoteSuccess", updateNoteSuccess);
        return new ModelAndView("redirect:/home?active=notes", (ModelMap) model);
    }
}
