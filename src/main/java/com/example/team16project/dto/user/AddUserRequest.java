package com.example.team16project.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;
}
