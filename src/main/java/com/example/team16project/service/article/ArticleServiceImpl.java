package com.example.team16project.service.article;

import com.example.team16project.domain.reply.Reply;
import com.example.team16project.domain.user.User;
import com.example.team16project.dto.article.request.ArticleWithIdForm;
import com.example.team16project.dto.article.response.ArticleDto;
import com.example.team16project.dto.article.request.ArticleForm;
import com.example.team16project.repository.article.ArticleRepository;
import com.example.team16project.domain.article.Article;
import com.example.team16project.repository.reply.ReplyRepository;
import com.example.team16project.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;
    private final ReplyService replyService;

    @Transactional(readOnly = true)
    @Override
    public ArticleDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + articleId));
        List<Reply> replys = replyRepository.findByArticleArticleIdAndReplyReplyId(articleId, null);
        return ArticleDto.toDto(article, replys, replyService);
    }

    @Override
    public List<Article> getArticles() {
        List<Article> all = articleRepository.findAll();
        return all;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDto> getArticles(int page, int pageSize) {
        List<Article> articles = articleRepository.selectAllArticles(pageSize, (page - 1) * pageSize);
        List<ArticleDto> collect = articles.stream()
                .map(a -> ArticleDto.toDto(a, replyRepository.findByArticleArticleId(a.getArticleId()),replyService))
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

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDto> searchArticles(int page, int pageSize, String query, String option) {

        List<Article> articles = new ArrayList();

        switch (option) {
            case "title":
                articles = articleRepository.searchBoardsByTitle(pageSize, (page - 1) * pageSize, query);
                break;

            case "contents":
                articles = articleRepository.searchBoardsByContents(pageSize, (page - 1) * pageSize, query);
                break;
        }

        List<ArticleDto> collect = articles.stream()
                .map(a -> ArticleDto.toDto(a, replyRepository.findByArticleArticleId(a.getArticleId()), replyService))
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional(readOnly = true)
    @Override
    public int getSearchPages(int pageSize, String query, String option) {
        int searchPages = 0;
        switch (option) {
            case "title":
                searchPages =  articleRepository.searchPagesByTitle(pageSize, query);
                break;

            case "contents":
                searchPages = articleRepository.searchPagesByContents(pageSize, query);
                break;
        }
        return searchPages;
    }
}
