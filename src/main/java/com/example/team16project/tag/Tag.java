package com.example.team16project.tag;

import com.example.team16project.user.User;
import jakarta.persistence.*;

@Entity
public class Tag {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    String name;
}
