package com.example.team16project.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private String username;
    private Long id;
    private String email;
    private String nickname;

    public UserInfo(UserInfo userInfo) {
    }

    public UserInfo(String email, String username, String nickname) {
        this.email = email;
        this.nickname = nickname;
        this.username = username;
    }
}