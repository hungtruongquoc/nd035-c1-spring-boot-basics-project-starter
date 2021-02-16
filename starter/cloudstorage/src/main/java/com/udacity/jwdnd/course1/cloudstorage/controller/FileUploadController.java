package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/file-upload")
@EnableWebSecurity
public class FileUploadController {

    private final FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping()
    public ModelAndView handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) {
        int result = this.fileService.createFile(new File(null, fileUpload.getOriginalFilename(),
                fileUpload.getContentType(), fileUpload.getSize(), null));
        if(1 != result) {
            model.addAttribute("errorMessage", "Failed to upload the selected file!");
        }
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }
}
