package com.example.authenticatingldapinitial;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home2Controller {

    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }
}
