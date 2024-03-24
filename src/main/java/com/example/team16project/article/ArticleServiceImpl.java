package com.example.team16project.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    @Override
    public ArticleDto getArticle(int articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + articleId));
        return ArticleDto.toDto(article);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDto> getArticles(int page, int pageSize) {
        List<ArticleDto> collect = articleRepository.selectAllCoteBaords(pageSize, (page - 1) * pageSize).stream().map(a -> ArticleDto.toDto(a))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public int getTotalPages(int pageSize) {
        return articleRepository.selectTotalPages(pageSize);
    }





}
