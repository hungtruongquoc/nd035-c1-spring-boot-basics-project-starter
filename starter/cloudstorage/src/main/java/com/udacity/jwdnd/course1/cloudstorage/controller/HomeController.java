package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
@EnableWebSecurity
public class HomeController {

    private final FileService fileService;

    private final NoteService noteService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(FileService fileService, NoteService noteService) {
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @GetMapping()
    public String homeView(Model model, @ModelAttribute("active") @RequestParam(required = false) String active) {
        logger.info("Error message: {}", model.getAttribute("errorMessage"));
        model.addAttribute("active", active);
        model.addAttribute("files", this.fileService.getAllFiles());
        model.addAttribute("notes", this.noteService.getAll());
        model.addAttribute("newNote", new Note());
        return "home";
    }
}
