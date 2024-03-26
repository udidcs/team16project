package com.example.team16project.controller.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AddUserRequest;
import com.example.team16project.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);
        return "redirect:/login";
    }

}
