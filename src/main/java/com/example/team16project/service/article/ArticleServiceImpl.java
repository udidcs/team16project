package com.example.team16project.service.article;

import com.example.team16project.domain.reply.Reply;
import com.example.team16project.domain.user.User;
import com.example.team16project.dto.article.request.ArticleWithIdForm;
import com.example.team16project.dto.article.response.ArticleDto;
import com.example.team16project.dto.article.request.ArticleForm;
import com.example.team16project.repository.article.ArticleRepository;
import com.example.team16project.domain.article.Article;
import com.example.team16project.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    @Override
    public ArticleDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + articleId));
        List<Reply> replys = replyRepository.findByArticleArticleId(articleId);
        return ArticleDto.toDto(article, replys);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDto> getArticles(int page, int pageSize) {
        List<Article> articles = articleRepository.selectAllCoteBaords(pageSize, (page - 1) * pageSize);
        List<ArticleDto> collect = articles.stream()
                .map(a -> ArticleDto.toDto(a, replyRepository.findByArticleArticleId(a.getArticleId())))
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional(readOnly = true)
    @Override
    public int getTotalPages(int pageSize) {
        return articleRepository.selectTotalPages(pageSize);
    }

    @Transactional
    @Override
    public Article saveArticle(@ModelAttribute ArticleForm articleForm, User user) {

        Article entity = ArticleForm.toEntity(articleForm);
        entity.setUser(user);

        Article save = articleRepository.save(entity);
        return save;
    }

    @Transactional
    @Override
    public void editArticle(ArticleWithIdForm articleWithIdForm) {
        Optional<Article> byId = articleRepository.findById(articleWithIdForm.getArticleId());
        Article article = byId.orElseThrow();
        article.setTitle(articleWithIdForm.getTitle());
        article.setContents(articleWithIdForm.getContents());
    }

    @Transactional
    @Override
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }


}
