package com.example.team16project.dto.article.response;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.reply.Reply;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ArticleDto {
    private List<Reply> replys;
    private Long userId;
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
                .userId(article.getUser().getUserId())
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
