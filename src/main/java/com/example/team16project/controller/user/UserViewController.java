package com.example.team16project.controller.user;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "프로필 사진 편집 페이지", description = "프로필 사진 변경 페이지로 이동")
    @GetMapping("/user/myprofile")
    public String myProfile(){
        return "user/myprofile";
    }

    @Operation(summary = "비밀번호 편집 페이지", description = "비밀번호 변경 페이지로 이동")
    @GetMapping("/user/mypassword")
    public String myPassword(){
        return "user/mypassword";
    }

    @GetMapping("/error/403")
    public String accessDeniedError() {
        return "/user/403error";
    }
}
