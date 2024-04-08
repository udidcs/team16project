package com.example.team16project.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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

    @Schema(description = "변경할 닉네임", minLength = 1, maxLength = 10, example = "새로운회원이름")
    @NotBlank
    @Size(min = 1, max = 10, message = "닉네임은 1~10글자 사이로 입력해주세요")
    private String nickname;

    @Schema(description = "변경할 닉네임", allowableValues = {"JUNIOR", "SENIOR"})
    private String role;
}
