package com.example.team16project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPasswordRequest {

    @Schema(description = "현재 비밀번호", example = "qwer1234", minLength = 8)
    @NotBlank
    @Pattern(message = "현재 비밀번호의 형식이 잘못되었습니다.", regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$")
    private String currentPassword;

    @Schema(description = "새로운 비밀번호", example = "ASDF5678", minLength = 8)
    @NotBlank
    @Pattern(message = "새로운 비밀번호의 형식이 잘못되었습니다.", regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$")
    private String newPassword;
}
