package com.example.team16project.dto.userinfo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private String name;
    private String password;

    public UserInfo(UserInfo userInfo) {
    }
}