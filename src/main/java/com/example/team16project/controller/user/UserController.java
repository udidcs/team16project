package com.example.team16project.controller.user;

import com.example.team16project.dto.user.*;
import com.example.team16project.service.user.UserProfileImageService;
import com.example.team16project.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "User", description = "회원 API")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserProfileImageService userProfileImageService;

    @Operation(summary = "회원가입", description = "아이디, 비밀번호, 닉네임을 입력해서 회원가입을 합니다.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "입력받은 유저 정보", required = true, content = @Content(schema = @Schema(implementation = AddUserRequest.class)))
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "회원가입에 성공하였습니다.", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "입력받은 값이 양식과 맞지 않습니다.", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "409", description = "이메일 또는 닉네임이 이미 사용중입니다.", content = @Content(mediaType = "text/plain"))}
    )
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
        userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }

    @Operation(summary = "마이페이지", description = "마이페이지로 이동합니다. 현재 로그인된 유저 정보를 표시해줍니다.")
    @GetMapping("/user/mypage")
    public String findUser(Authentication authentication, Model model) {
        UserInfo userInfo = userService.findUserInfo(authentication);
        model.addAttribute("userInfo", userInfo);
        return "user/mypage";
    }

    @Operation(summary = "닉네임 변경 페이지", description = "닉네임 변경 페이지로 이동합니다. 로그인된 회원의 닉네임을 표시해줍니다.")
    @GetMapping("/user/myinfo")
    public String findUserInMyinfo(Authentication authentication, Model model) {
        UserInfo userInfo = userService.findUserInfo(authentication);
        model.addAttribute("user", userInfo);
        return "user/myinfo";
    }

    @Operation(summary = "닉네임 변경", description = "변경할 닉네임을 입력 후 변경버튼을 누를 경우 변경사항 반영")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청에 성공하였습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "요청에 실패하였습니다.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/user/myinfo")
    @ResponseBody
    public Map<String, String> updateNickname(Authentication authentication, @RequestBody UpdateUserInfoRequest request) throws IOException {
        Map<String, String> response = new HashMap<>();
        try {
            userService.checkNicknameDuplicate(request.getNickname());
            UserInfo userInfo = userService.findUserInfo(authentication);
            userService.updateNickname(authentication, request.getNickname());
            userInfo.setNickname(request.getNickname());
            response.put("message", "success");

        } catch (Exception e) {
            response.put("message", "error");
        }
        return response;
    }


    @Operation(summary = "비밀번호 변경", description = "로그인된 사용자의 비밀번호를 변경합니다. 변경할 비밀번호는 현재 비밀번호와 달라야 합니다.")
    @ApiResponse(responseCode = "200", description = "처리 결과를 알려줍니다.", content = @Content(mediaType = "text/plain"))
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

    @Operation(summary = "회원탈퇴 페이지", description = "회원탈퇴 페이지로 이동합니다. 로그인된 회원의 이메일을 표시해줍니다.")
    @GetMapping("/user/mywithdraw")
    public String myWithdraw(Authentication authentication, Model model){
        UserInfo userInfo = userService.findUserInfo(authentication);
        model.addAttribute("user", userInfo);
        return "user/mywithdraw";
    }

    @Operation(summary = "회원탈퇴 요청", description = "로그인된 회원의 비밀번호를 다시 입력하여 회원탈퇴 요청을 합니다.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "입력받은 유저 정보", required = true, content = @Content(schema = @Schema(implementation = DeleteUserRequest.class)))
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "회원탈퇴 요청이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "입력한 비밀번호가 양식과 맞지 않습니다."),
            @ApiResponse(responseCode = "401", description = "비밀번호가 틀립니다.")}
    )
    @DeleteMapping("/user/delete")
    @ResponseBody
    public ResponseEntity<String> delete(@Valid DeleteUserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage().toString();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }
        try {
            userService.deleteUser(request);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        return ResponseEntity.ok("탈퇴 요청이 완료되었습니다.");
    }

    @Operation(summary = "로그인 시 유저 상태 확인", description = "로그인 시에 탈퇴 대기상태인 회원은 복구페이지로 이동시킵니다.")
    @GetMapping("/user/check")
    public String checkDeleted(Authentication authentication) {
        if (!userService.isDeleted(authentication)) {
            return "redirect:/articles";
        } else{
            return "user/recovery";
        }
    }

    @Operation(summary = "탈퇴 요청 취소", description = "복구페이지에서 탈퇴 요청을 취소합니다.")
    @GetMapping("/user/cancle")
    public void cancelUserDelete(Authentication authentication) {
        userService.recoveryUser(authentication);
    }

    @GetMapping("/user/images/{filename}")
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

    // 매일 자정마다 삭제 대기기간이 지난 회원의 정보를 삭제합니다.
    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOutdatedUsers() {
        userService.deleteOutdatedUsers();
    }
}
