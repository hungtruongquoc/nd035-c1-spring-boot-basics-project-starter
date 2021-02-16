package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/home")
@EnableWebSecurity
public class HomeController {

    private final FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
    public String homeView(Model model) {
        logger.info("Error message: {}", model.getAttribute("errorMessage"));
        model.addAttribute("files", this.fileService.getAllFiles());
        return "home";
    }
}
