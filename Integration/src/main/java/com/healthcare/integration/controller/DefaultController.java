package com.healthcare.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping("/")
    public String home(){
        return "Home...";
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello...";
    }
}
