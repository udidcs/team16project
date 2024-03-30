package com.example.team16project.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/user/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }
}
