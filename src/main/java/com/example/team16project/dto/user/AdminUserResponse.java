package com.example.team16project.dto.user;

import com.example.team16project.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminUserResponse {
    private String email;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String role;

    public AdminUserResponse(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.createdAt = user.getCreatedAt();
        this.deletedAt = user.getDeletedAt();
        this.role = user.getRole();
    }
}
