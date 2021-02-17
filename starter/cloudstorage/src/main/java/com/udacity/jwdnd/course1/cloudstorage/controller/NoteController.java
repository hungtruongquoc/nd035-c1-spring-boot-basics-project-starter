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
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Controller
@RequestMapping("/notes")
@EnableWebSecurity
public class NoteController {

    private final NoteService noteService;

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    private HashMap<String, Object> createNote(NoteForm note) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        String noteDataOperationError = "Failed to create this note. Please try again later.";
        boolean noteDataOperationSuccess = false;
        Note targetNote;

        targetNote = new Note();
        targetNote.setNoteTitle(note.getNoteTitle()).setNoteDescription(note.getNoteDescription());

        if (1 == this.performDataOperation("createNote", targetNote)) {
            noteDataOperationSuccess = true;
            noteDataOperationError = null;
        }

        return this.createModelViewData(noteDataOperationError, noteDataOperationSuccess);
    }

    private HashMap<String, Object> updateNote(NoteForm note) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        String noteDataOperationError = "Failed to update provided note. Please try again later.";
        boolean noteDataOperationSuccess = false;
        Note targetNote;

        targetNote = this.noteService.getById(note.getNoteId());
        targetNote.setNoteTitle(note.getNoteTitle()).setNoteDescription(note.getNoteDescription());

        if (1 == this.performDataOperation("updateNote", targetNote)) {
            noteDataOperationSuccess = true;
            noteDataOperationError = null;
        }

        return this.createModelViewData(noteDataOperationError, noteDataOperationSuccess);
    }

    private Integer performDataOperation(String methodName, Note targetNote)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method performDataManipulation = NoteService.class.getMethod(methodName, Note.class);
        return (Integer) performDataManipulation.invoke(this.noteService, targetNote);
    }

    private HashMap<String, Object> createModelViewData(String errorMessage, boolean success) {
        HashMap<String, Object> viewData = new HashMap<>();
        viewData.put("noteErrorMessage", errorMessage);
        viewData.put("noteSuccess", success);
        return viewData;
    }

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public ModelAndView createNote(Model model, @ModelAttribute("newNote") NoteForm note) {

        HashMap<String, Object> viewData;

        try {
            if (note.isValid()) {
                if (null == note.getNoteId()) {
                    viewData = this.createNote(note);
                } else {
                    viewData = this.updateNote(note);
                }
            } else {
                viewData = this.createModelViewData("Input is invalid", false);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException invocationError) {
            viewData = this.createModelViewData("Failed to work with note. Please try again later", false);
            logger.error("Cannot perform either update/create method using method invocation.");
            logger.error("Please make sure: the method does exist/the object is instantiated/the method is accessible");
        } catch (Exception exception) {
            viewData = this.createModelViewData("Failed to work with note. Please try again later", false);
            logger.error("Cannot create or update the provided note. Maybe some database error.");
        }

        viewData.put("active", "notes");
        model.addAllAttributes(viewData);
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }
}
