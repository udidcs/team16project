package com.example.team16project.controller.user;

import com.example.team16project.dto.user.AdminUserRequest;
import com.example.team16project.dto.user.AdminUserResponse;
import com.example.team16project.service.user.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin")
    public String findAllUsers(Model model) {
        List<AdminUserResponse> users = adminService.findAllUsers();
        model.addAttribute("users", users);
        return "user/admin";
    }

    @GetMapping("/admin/{id}")
    public String findOneUser(@PathVariable Long id, Model model) {
        AdminUserResponse user = adminService.findOneUser(id);
        model.addAttribute("user", user);
        return "user/adminUser";
    }

    @PostMapping("/admin/{id}")
    public String updateUserByAdmin(@PathVariable String id, AdminUserRequest request) {
        adminService.updateUserInfo(Long.parseLong(id), request);
        return "redirect:/admin/" + id;
    }
}
