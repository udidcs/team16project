package com.example.team16project.controller.user;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.repository.user.UserRepository;
import com.example.team16project.service.user.UserProfileImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserInfoController {

    private final UserRepository userRepository;
    private final UserProfileImageService userProfileImageService;

    @GetMapping("/user/mypage")
    public String myPage(Principal principal, Model model){
        String username = principal.getName();
        User user = userRepository.findByEmail(username).get();
        UserInfo userInfo = new UserInfo(user.getEmail(), user.getUsername(), user.getNickname(), user.getProfileImage());
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

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {

        try {
            byte[] imageBytes = userProfileImageService.getImage(filename);

            // 이미지 파일의 MIME 타입 가져오기
            MediaType mediaType = MediaType.IMAGE_JPEG; // 기본값으로 JPEG 설정

            // 파일 이름으로부터 MIME 타입 추론
            if (filename.toLowerCase().endsWith(".png")) {
                mediaType = MediaType.IMAGE_PNG;
            } else if (filename.toLowerCase().endsWith(".gif")) {
                mediaType = MediaType.IMAGE_GIF;
            } // 다른 형식이 필요한 경우에도 이와 같이 추가 가능

            // 응답 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);

            // 바이트 배열과 헤더를 이용하여 ResponseEntity 생성 후 반환
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // 파일을 읽을 수 없는 경우 404 응답 반환
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @PostMapping("/user/myprofile")
    public String uploadImage(@RequestParam MultipartFile pdtimg, Principal principal) throws InterruptedException {

        String imageFileName = userProfileImageService.saveImageToFile(pdtimg);
        userProfileImageService.saveImageToDB(imageFileName, principal);
        return "redirect:/user/mypage";
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
