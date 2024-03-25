package com.example.team16project.dto.article;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ArticleDto {
    Integer articleId;
    User user;
    String title;
    String contents;
    Timestamp createdAt;
    Integer likeCount;
    Integer viewCount;
    Timestamp updatedAt;

    public static ArticleDto toDto(Article article) {
        return ArticleDto.builder()
                .articleId(article.getArticleId())
                .user(article.getUser())
                .title(article.getTitle())
                .contents(article.getContents())
                .createdAt(article.getCreatedAt())
                .likeCount(article.getLikeCount())
                .viewCount(article.getViewCount())
                .updatedAt(article.getUpdatedAt()).build();
    }

}
