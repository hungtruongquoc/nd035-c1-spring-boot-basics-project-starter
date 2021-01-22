package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String getHomePage(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        return "signup";
    }

    @GetMapping("/result")
    public String getResultPage(Model model) {
        return "result";
    }

}
