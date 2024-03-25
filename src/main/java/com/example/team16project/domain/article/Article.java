package com.example.team16project.domain.article;

import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;
    String title;
    String contents;
    Timestamp createdAt;
    Integer likeCount;
    Integer viewCount;
    Timestamp updatedAt;
}
