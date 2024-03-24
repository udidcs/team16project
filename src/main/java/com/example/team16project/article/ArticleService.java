package com.example.team16project.article;


import com.example.team16project.article.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto getArticle(int id);
    List<ArticleDto> getArticles(int page, int pageSize);
    int getTotalPages(int pageSize);


}

