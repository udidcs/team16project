package com.example.team16project.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/user/login")
    public String login(Authentication authentication) {
        if (authentication != null) {return "redirect:/articles";}
        return "user/login";
    }

    @GetMapping("/user/signup")
    public String signup(Authentication authentication) {
        if (authentication != null) {return "redirect:/articles";}
        return "user/signup";
    }

    @GetMapping("/user/myprofile")
    public String myProfile(){
        return "user/myprofile";
    }

    @GetMapping("/user/myinfo")
    public String myInfo() {
        return "user/myinfo";
    }

    @GetMapping("/user/mypassword")
    public String myPassword(){
        return "user/mypassword";
    }

    @GetMapping("/user/mywithdraw")
    public String myWithdraw(){
        return "user/mywithdraw";
    }

    @GetMapping("/error/403")
    public String accessDeniedError() {
        return "/user/403error";
    }
}
