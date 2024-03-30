package com.example.team16project.dto.user;

import com.example.team16project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRequest {
    private String nickname;
    private String role;

    public AdminUserRequest(User user) {
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }
}
