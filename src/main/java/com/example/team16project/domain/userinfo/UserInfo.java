package com.example.team16project.domain.userinfo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class UserInfo {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user;

    @Column(name="name")
    private String name;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="profile_image")
    private String profileImage;

}
