package com.example.team16project.controller.user;

import com.example.team16project.dto.user.AdminUserResponse;
import com.example.team16project.service.user.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String findAllUsers(Model model) {
        List<AdminUserResponse> users = adminService.findAllUsers();
        model.addAttribute("users", users);
        return "user/admin";
    }

}

// 1번안: 수정 모드를 따로 만들어서 수정모드 키면 데이터 수정 가능하고, 저장 버튼을 누르면 수정된 데이터를 받아서 db에 적용하고 수정된 데이터를 불러옴

// 2번안: 개별적으로 수정 가능한 버튼을 만들어서 버튼을 누르면 데이터가 즉시 변경(바꾼것만 교체 가능하면 좋을텐데 될까?)
