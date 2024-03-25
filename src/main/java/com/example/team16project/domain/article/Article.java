package com.example.team16project.domain.article;

import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long articleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @Column(length = 40, nullable = false)
    String title;

    @Column(length = 3000, nullable = false)
    String contents;

    @Column(nullable = false)
    LocalDateTime createdAt;

    @ColumnDefault(value = "null")
    LocalDateTime updatedAt;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    Integer viewCount;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    Integer likeCount;

}
