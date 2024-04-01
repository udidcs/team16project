package com.example.team16project.controller.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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
