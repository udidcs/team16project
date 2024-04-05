package com.example.team16project.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRequest {

    @NotBlank
    @Size(min = 1, max = 10, message = "닉네임은 1~10글자 사이로 입력해주세요")
    private String nickname;

    private String role;
}
