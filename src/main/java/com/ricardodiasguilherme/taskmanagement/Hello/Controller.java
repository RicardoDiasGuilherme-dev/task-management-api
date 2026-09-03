package com.ricardodiasguilherme.taskmanagement.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Task Management API is running";
    }
}
