package com.example.team16project.controller.user;


import com.example.team16project.dto.user.AddUserRequest;
import com.example.team16project.dto.user.UpdateUserPasswordRequest;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.service.user.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    @ResponseBody
    public ResponseEntity<String> signup(AddUserRequest request) {
        try {
            userService.checkEmailDuplicate(request.getEmail());
            userService.checkNicknameDuplicate(request.getNickname());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        try {
            userService.save(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @GetMapping("/user/mypage")
    public String findUser(Authentication authentication, Model model) {
        UserInfo userInfo = userService.findUserInfo(authentication);
        model.addAttribute("userInfo", userInfo);
        return "user/mypage";
    }

    @PatchMapping("/user/update")
    @ResponseBody
    public String changePassword(@Valid UpdateUserPasswordRequest request, Authentication authentication) {
        return userService.updatePassword(request, authentication);
    }

    @GetMapping("/user/check")
    public String checkDeleted(Authentication authentication) {
        if (!userService.isDeleted(authentication)) {
            return "redirect:/articles";
        } else{
            return "user/recovery";
        }
    }

    @GetMapping("/user/cancle")
    public void cancleUserDelete(Authentication authentication) {
        userService.recoveryUser(authentication);
    }

//    @ResponseBody
//    @GetMapping("/images/{filename}")
//    public Resource returnimage(@PathVariable String filename) throws MalformedURLException {
//        String path = "file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\" + filename;
////                String path = "file:/home/ec2-user/jenkins/images/" + filename;
//        return new UrlResource(path);
//    }
//
//    @PostMapping("/user/myprofile")
//    public String uploadProduct(@RequestParam MultipartFile pdtimg) throws InterruptedException {
//        try {
//            int i =  pdtimg.getOriginalFilename().lastIndexOf('.');
//            String substring = pdtimg.getOriginalFilename().substring(i + 1);
//            String str = String.valueOf(UUID.randomUUID().toString()) + "." + substring;
////          pdtimg.transferTo(new File(System.getProperty("user.dir")
////                    + "\\src\\main\\resources\\static\\images\\" + str));
//            pdtimg.transferTo(new File("/home/ec2-user/jenkins/images/" +str));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return "redirect:/home";
//    }


}
