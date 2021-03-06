package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Controller
@RequestMapping("/notes")
@EnableWebSecurity
public class NoteController extends BaseController {

    private final NoteService noteService;
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    private HashMap<String, Object> createModelViewData(String message, boolean success) {
        return super.createModelViewData("note", message, success);
    }

    private HashMap<String, Object> createNote(NoteForm note) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        String noteDataOperationError = "Failed to create this note. Please try again later.";
        boolean noteDataOperationSuccess = false;

        if (1 == this.performDataOperation("createNote", note)) {
            noteDataOperationSuccess = true;
            noteDataOperationError = "New note was created.";
        }

        return this.createModelViewData(noteDataOperationError, noteDataOperationSuccess);
    }

    private HashMap<String, Object> updateNote(NoteForm note) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        String noteDataOperationError = "Failed to update provided note. Please try again later.";
        boolean noteDataOperationSuccess = false;

        if (1 == this.performDataOperation("updateNote", note)) {
            noteDataOperationSuccess = true;
            noteDataOperationError = "Note was updated successfully";
        }

        return this.createModelViewData(noteDataOperationError, noteDataOperationSuccess);
    }

    private Integer performDataOperation(String methodName, NoteForm noteForm)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Note targetNote;
        Method performDataManipulation = NoteService.class.getMethod(methodName, Note.class);

        if (noteForm.hasNoteId()) {
            targetNote = this.noteService.getById(noteForm.getNoteId());
        } else {
            targetNote = new Note();
        }
        targetNote.setNoteTitle(noteForm.getNoteTitle()).setUserId(noteForm.getUserId())
                .setNoteDescription(noteForm.getNoteDescription());

        return (Integer) performDataManipulation.invoke(this.noteService, targetNote);
    }

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping()
    public ModelAndView createNote(Model model, @ModelAttribute("newNote") NoteForm note,
                                   @RequestParam(name = "action", required = false) String action) {

        HashMap<String, Object> viewData;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUser((String) auth.getPrincipal());

        if (null != action && action.contains("delete")) {
            if (1 == this.noteService.deleteNote(note.getNoteId())) {
                viewData = this.createModelViewData("Note was deleted successfully.", true);
            }
            else {
                viewData = this.createModelViewData("Failed to delete the note. Please try again later.", false);
            }
        }
        else {
            try {
                if (note.isValid()) {
                    if (null == note.getNoteId()) {
                        note.setUserId(currentUser.getUserId());
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
        }

        viewData.put("active", "notes");
        model.addAllAttributes(viewData);
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }
}
