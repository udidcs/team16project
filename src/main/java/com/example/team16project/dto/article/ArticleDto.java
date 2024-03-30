package com.example.team16project.dto.article;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.reply.Reply;
import com.example.team16project.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ArticleDto {
    private List<Reply> replys;
    private Long articleId;
    private String email;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private Integer likeCount;
    private Integer viewCount;

    public static ArticleDto toDto(Article article, List<Reply> replys) {
        return ArticleDto.builder()
                .replys(replys)
                .articleId(article.getArticleId())
                .email(article.getUser().getEmail())
                .title(article.getTitle())
                .contents(article.getContents())
                .createdAt(article.getCreatedAt())
                .likeCount(article.getLikeCount())
                .viewCount(article.getViewCount())
                .build();
    }

}
