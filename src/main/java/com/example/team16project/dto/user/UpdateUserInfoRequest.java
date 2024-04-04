package com.example.team16project.dto.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UpdateUserInfoRequest {
    private Long id;
    private String nickname;

    public UpdateUserInfoRequest(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
