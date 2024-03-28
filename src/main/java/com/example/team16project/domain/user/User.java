package com.example.team16project.domain.user;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @Column(name = "user_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "nickname", length = 10)
    private String nickname;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @ColumnDefault("'no_image'")
    @Column(name = "profile_image")
    private String profileImage;

    // JUNIOR: 기본, SENIOR: 등업, ADMIN: 관리자
    @Column(name = "role", length = 10)
    private String role = "JUNIOR";

    @Transient
    private List<String> auths = new ArrayList<>(Arrays.asList("JUNIOR", "SENIOR", "ADMIN"));

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        int userLevel = auths.indexOf(role);
        for (int i = 0; i <= userLevel; i++) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+auths.get(i)));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 유저 등급 올리기, 최고등급일 때 쓰면 예외처리를 해서 알려줘야 할까?
    public String upgrade() {
        int userLevel = auths.indexOf(role);
        if (userLevel < auths.indexOf("SENIOR")) {
            userLevel++;
            this.role = auths.get(userLevel);
            return nickname+" 회원의 등급이"+ role + " 으(로) 변경되었습니다.";
        } else return nickname + " 회원은 이미 최고 등급 회원입니다.";
    }

}
