package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/home")
@EnableWebSecurity
public class HomeController {

    private final FileService fileService;

    private final NoteService noteService;

    private final CredentialService credentialService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialSrv) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialSrv;
    }

    @GetMapping()
    public String homeView(Model model) {
        model.addAttribute("files", this.fileService.getAllFiles())
                .addAttribute("notes", this.noteService.getAll())
                .addAttribute("credentials", this.credentialService.getAll())
                .addAttribute("errorMessage", null)
                .addAttribute("newNote", new Note());
        return "home";
    }
}
