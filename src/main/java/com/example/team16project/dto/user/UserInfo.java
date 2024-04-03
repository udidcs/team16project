package com.example.team16project.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private String email;
    private String nickname;
    private LocalDateTime createdAt;

    public UserInfo(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.createdAt = user.getCreatedAt();
    }
}