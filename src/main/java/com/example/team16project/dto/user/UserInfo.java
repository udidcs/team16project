package com.example.team16project.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private String username;
    private Long userId;
    private String email;
    private String nickname;
    private LocalDateTime createdAt;

    public UserInfo(String email, String nickname, Long userId, LocalDateTime createdAt) {
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public UserInfo(String nickname) {
    }

    public UserInfo(String nickname, String email) {
    }
}