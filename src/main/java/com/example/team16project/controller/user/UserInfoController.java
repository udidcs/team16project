package com.example.team16project.controller.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.UUID;

@Controller
public class UserInfoController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/mypage")
    public String myPage(Principal principal, Model model){
        String username = principal.getName();
        User user = userRepository.findByEmail(username).get();
        UserInfo userInfo = new UserInfo(user.getEmail(), user.getUsername(), user.getNickname());
        model.addAttribute("userInfo", userInfo);
        return "user/mypage";
    }

//        @RequestMapping("/mypage")
//    public String myPage(Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);
//        model.addAttribute("userInfo", userInfo);
//        return "/user/mypage";
//    }

    @GetMapping("/user/myprofile")
    public String myProfile(){

        return "user/myprofile";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource returnimage(@PathVariable String filename) throws MalformedURLException {
        String path = "file:\\" + System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\" + filename;
//                String path = "file:/home/ec2-user/jenkins/images/" + filename;
        return new UrlResource(path);
    }

    @PostMapping("/user/myprofile")
    public String uploadProduct(@RequestParam MultipartFile pdtimg) throws InterruptedException {
        try {
            int i =  pdtimg.getOriginalFilename().lastIndexOf('.');
            String substring = pdtimg.getOriginalFilename().substring(i + 1);
            String str = String.valueOf(UUID.randomUUID().toString()) + "." + substring;
//          pdtimg.transferTo(new File(System.getProperty("user.dir")
//                    + "\\src\\main\\resources\\static\\images\\" + str));
            pdtimg.transferTo(new File("/home/ec2-user/jenkins/images/" +str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/home";
    }


    @GetMapping("/user/myinfo")
    public String myInfo(){

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
}
