package com.antares.jinsei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antares.jinsei.security.JnUserDetailsService;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class.getName());

    @Autowired
    private JnUserDetailsService userDetailsService;

    @GetMapping("/hello")
    public String hello() {
        logger.info("GET /hello");
        return "Hello!";
    }

    @PostMapping("/hello")
    public String helloName(@RequestParam String name) {
        logger.info("POST /hello?name=" + name);
        return "Hello " + name + "!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        return userDetailsService.createUser(username, password);
    }

}
