package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.form.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@EnableWebSecurity
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @GetMapping()
    public String loginView(@ModelAttribute("user") LoginForm loginForm, Model model) {
        String signInError = null;
        model.addAttribute("signInError", signInError);
        return "login";
    }

//    @PostMapping()
//    public String loginUser(@ModelAttribute("user") LoginForm loginForm, Model model) {
//        String signInError = "Null";
//        logger.info("Login data: ", loginForm);
//        model.addAttribute("signInError", signInError);
//        return "login";
//    }
}
