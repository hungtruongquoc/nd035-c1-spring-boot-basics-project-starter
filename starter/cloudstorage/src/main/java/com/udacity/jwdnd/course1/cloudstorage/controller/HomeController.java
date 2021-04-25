package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final CredentialService credentialService;

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    public HomeController(FileService fileService, NoteService noteService, UserService userService,
                          CredentialService credentialSrv) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialSrv;
        this.userService = userService;
    }

    @GetMapping()
    public String homeView(Model model, @RequestParam(name = "fileId", required = false) Integer fileId,
                           @RequestParam(name = "currentCredential", required = false) Integer credentialId,
                           @RequestParam(name = "fileDeleted", required = false) Integer fileDeleted) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser;
        Credential currentCredential = new Credential();
        File currentFile = null;
        Credential[] credentials = null;

        if (null != auth) {
            currentUser = userService.getUser((String) auth.getPrincipal());
            if (null != currentUser) {
                credentials = this.credentialService.getCredentialsByUser(currentUser.getUserId());
            }
        }
        if (null != credentialId) {
            currentCredential = credentialService.getById(credentialId);
        }
        if (null != fileId) {
            currentFile = fileService.findById(fileId);
        }

        model.addAttribute("files", this.fileService.getAllFiles())
                .addAttribute("notes", this.noteService.getAll())
                .addAttribute("credentials", credentials)
                .addAttribute("errorMessage", null)
                .addAttribute("newCredential", new Credential())
                .addAttribute("currentCredential", currentCredential)
                .addAttribute("currentFile", currentFile)
                .addAttribute("fileDeleteSuccess", null != fileDeleted && 1 == fileDeleted ? "The file was deleted successfully." : null)
                .addAttribute("newNote", new Note());
        return "home";
    }
}
