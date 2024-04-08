package com.example.team16project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "회원가입 요청 DTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    @Schema(description = "이메일", example = "test@test.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "비밀번호", example = "qwer1234", minLength = 8)
    @NotBlank
    @Pattern(message = "현재 비밀번호의 형식이 잘못되었습니다.", regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$")
    private String password;

    @Schema(description = "닉네임", minLength = 1, maxLength = 10, example = "신규회원")
    @NotBlank
    @Size(min = 1, max = 10)
    private String nickname;
}
