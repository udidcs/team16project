package com.example.team16project.service.article;


import com.example.team16project.dto.article.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto getArticle(Long articleId);
    List<ArticleDto> getArticles(int page, int pageSize);
    int getTotalPages(int pageSize);


}

