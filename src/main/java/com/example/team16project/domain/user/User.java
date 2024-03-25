package com.example.team16project.domain.user;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    @Column(length = 20, nullable = false)
    String email;

    @Column(length = 10, nullable = false)
    String name;

    @Column(length = 15, nullable = false)
    String password;

    @Column(nullable = false)
    LocalDateTime createdAt;

    @ColumnDefault(value = "null")
    LocalDateTime deletedAt;

    @ColumnDefault(value = "null")
    @Column(length = 30)
    String profileImage;

    @ColumnDefault(value = "\"USER\"")
    @Column(length = 10)
    String role;

}
