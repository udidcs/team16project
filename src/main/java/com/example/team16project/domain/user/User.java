package com.example.team16project.domain.user;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    String email;
    String name;
    String password;
    LocalDateTime createdAt;
    LocalDateTime deletedAt;
    String profileImage;
    String role;

}
