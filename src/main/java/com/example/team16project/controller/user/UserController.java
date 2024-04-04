package com.example.team16project.controller.user;


import com.example.team16project.domain.user.User;
import com.example.team16project.dto.user.AddUserRequest;
import com.example.team16project.dto.user.UpdateUserInfoRequest;
import com.example.team16project.dto.user.UpdateUserPasswordRequest;
import com.example.team16project.dto.user.UserInfo;
import com.example.team16project.repository.user.UserRepository;
import com.example.team16project.service.user.UserInfoService;
import com.example.team16project.service.user.UserProfileImageService;
import com.example.team16project.service.user.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserProfileImageService userProfileImageService;

    @PostMapping("/user/signup")
    @ResponseBody
    public ResponseEntity<String> signup(@Valid AddUserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMsg.append(error.getDefaultMessage()).append(",");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg.toString());
        }
        try {
            userService.checkEmailDuplicate(request.getEmail());
            userService.checkNicknameDuplicate(request.getNickname());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }

    @GetMapping("/user/mypage")
    public String findUser(Authentication authentication, Model model) {
        UserInfo userInfo = userService.findUserInfo(authentication);
        model.addAttribute("userInfo", userInfo);
        return "user/mypage";
    }

    @PatchMapping("/user/update")
    @ResponseBody
    public String changePassword(@Valid UpdateUserPasswordRequest request, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMsg.append(fieldError.getDefaultMessage()).append(", ");
            });
            return errorMsg.toString();
        }
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

}
