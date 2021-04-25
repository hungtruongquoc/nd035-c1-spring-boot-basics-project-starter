package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/files")
@EnableWebSecurity
public class FileController {

    private final FileService fileService;

    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDelete(Model model, @PathVariable Integer id) {
        model.addAttribute("fileId", id);
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteFile(Model model, @PathVariable Integer id) {
        Integer result = fileService.delete(id);
        model.addAttribute("fileId", id);
        model.addAttribute("fileDeleted", result);
        return new ModelAndView("redirect:/home", (ModelMap) model);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id, Authentication authentication) throws UnsupportedEncodingException {
        User currentUser = userService.getUser((String) authentication.getPrincipal());
        File file = fileService.getFile(id, currentUser.getUserId());
        String fileName = URLEncoder.encode(file.getFileName(), StandardCharsets.UTF_8);
        fileName = URLDecoder.decode(fileName, "ISO8859_1");
        // Set header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=" + fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .contentLength(file.getFileSize())
                .headers(headers)
                .body(file.getFileData());
    }
}
