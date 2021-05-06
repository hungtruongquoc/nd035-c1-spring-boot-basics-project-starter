package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileExistenceException;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/file-upload")
@EnableWebSecurity
public class FileUploadController {

    private final FileService fileService;

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    private final UserService userService;

    public FileUploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping()
    public ModelAndView handleFileUpload(Authentication authentication,
                                         @RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
        User currentUser = userService.getUser((String) authentication.getPrincipal());
        boolean hasNoError = true;

        if (fileUpload.isEmpty()) {
            model.addAttribute("errorMessage", "No file found. Please select a file to upload!");
            hasNoError = false;
        }
        if (hasNoError && fileUpload.getSize() >= 5L * 1024 * 1024 * 1024) {
            model.addAttribute("errorMessage", "Provided file is too big. Please select a smaller file to upload!");
            hasNoError = false;
        }
        if (hasNoError) {
            try {
                int result = this.fileService.createFile(new File(null, fileUpload.getOriginalFilename(),
                        fileUpload.getContentType(), fileUpload.getSize(), currentUser.getUserId(), fileUpload.getBytes()));
                if (1 != result) {
                    model.addAttribute("errorMessage", "Failed to upload the selected file!");
                } else {
                    model.addAttribute("successUpload", "The file was uploaded successfully.");
                }
            } catch (FileExistenceException $ex) {
                model.addAttribute("errorMessage", $ex.getMessage());
            }
        }
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }
}
