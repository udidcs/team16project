package com.example.team16project.dto.user;

import jakarta.validation.constraints.Email;
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
public class AddUserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(message = "현재 비밀번호의 형식이 잘못되었습니다.", regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$")
    private String password;

    @NotBlank
    @Size(min = 1, max = 10)
    private String nickname;
}
