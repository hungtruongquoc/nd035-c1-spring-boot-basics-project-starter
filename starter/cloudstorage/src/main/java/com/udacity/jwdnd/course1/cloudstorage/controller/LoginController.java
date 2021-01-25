package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView(@ModelAttribute("user") LoginForm loginForm, Model model) {
        String signInError = null;
        model.addAttribute("signInError", signInError);
        return "login";
    }

    @PostMapping()
    public String loginUser(@ModelAttribute("user") LoginForm loginForm, Model model) {
        String signInError = null;
        model.addAttribute("signInError", signInError);
        return "login";
    }
}
