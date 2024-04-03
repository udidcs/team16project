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
}
