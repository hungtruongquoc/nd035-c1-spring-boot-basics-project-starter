package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
@EnableWebSecurity
public class CredentialController {
    private static final Logger logger = LoggerFactory.getLogger(CredentialController.class);

    public CredentialController() {

    }


}
