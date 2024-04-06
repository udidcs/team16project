package com.example.team16project.controller.user;

import com.example.team16project.dto.user.AdminUserRequest;
import com.example.team16project.dto.user.AdminUserResponse;
import com.example.team16project.service.user.AdminService;
import com.example.team16project.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

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

    @PatchMapping("/admin/{id}")
    @ResponseBody
    public ResponseEntity<String> updateUserByAdmin(@PathVariable String id, @Valid AdminUserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage().toString();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }
        try {
            userService.checkNicknameDuplicate(request.getNickname());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        adminService.updateUserInfo(Long.parseLong(id), request);

        return ResponseEntity.ok("회원정보 수정이 완료되었습니다.");
//        return "redirect:/admin/" + id;
    }
}
