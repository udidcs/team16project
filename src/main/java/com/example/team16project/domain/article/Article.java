package com.example.team16project.domain.article;

import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    LocalDateTime createdAt;
    Integer likeCount;
    Integer viewCount;
    LocalDateTime updatedAt;
}
