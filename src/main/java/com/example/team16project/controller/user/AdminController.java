package com.example.team16project.controller.user;

import com.example.team16project.dto.user.AdminUserRequest;
import com.example.team16project.dto.user.AdminUserResponse;
import com.example.team16project.service.user.AdminService;
import com.example.team16project.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Admin", description = "관리자 API")
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    private final AdminService adminService;

    @Operation(summary = "전체 회원 정보 조회", description = "관리자가 등록된 모든 회원의 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "전체 회원 정보를 성공적으로 불러왔습니다.", content = @Content(mediaType = "text/html"))
    @GetMapping("/admin")
    public String findAllUsers(Model model) {
        List<AdminUserResponse> users = adminService.findAllUsers();
        model.addAttribute("users", users);
        return "user/admin";
    }

    @Operation(summary = "회원 정보 조회", description = "관리자가 선택한 회원의 정보를 조회합니다.")
    @Parameter(name = "id", description = "회원 UID", example = "11")
    @ApiResponse(responseCode = "200", description = "선택한 회원의 정보를 성공적으로 불러왔습니다.", content = @Content(mediaType = "text/html"))
    @GetMapping("/admin/{id}")
    public String findOneUser(@PathVariable Long id, Model model) {
        AdminUserResponse user = adminService.findOneUser(id);
        model.addAttribute("user", user);
        return "user/adminUser";
    }

    @Operation(summary = "회원 정보 수정", description = "관리자가 선택한 회원의 정보를 수정합니다")
    @RequestBody(description = "변경할 회원 정보", required = true, content = @Content(schema = @Schema(implementation = AdminUserRequest.class)))
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "회원 정보 수정이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "변경할 닉네임이 양식에 맞지 않습니다."),
            @ApiResponse(responseCode = "409", description = "변경할 닉네임이 이미 사용중입니다.")}
    )
    @PatchMapping("/admin/{id}")
    @ResponseBody
    public ResponseEntity<String> updateUserByAdmin(@PathVariable String id, @Valid AdminUserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }
        try {
            userService.checkNicknameDuplicate(request.getNickname());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        adminService.updateUserInfo(Long.parseLong(id), request);

        return ResponseEntity.ok("회원정보 수정이 완료되었습니다.");
    }
}
