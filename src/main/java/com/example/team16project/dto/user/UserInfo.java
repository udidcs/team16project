package com.example.team16project.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private String nickname;

    public UserInfo(UserInfo userInfo) {
    }

    public UserInfo(String email, String name, String profileImage) {

    }
}