package com.example.team16project.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserPasswordRequest {
    @NotBlank
    private String currentPassword;

    @NotBlank
    @Pattern(message = "잘못된 비밀번호 형식입니다.", regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$")
    private String newPassword;
}
