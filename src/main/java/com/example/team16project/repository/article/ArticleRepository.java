package com.example.team16project.repository.article;

import com.example.team16project.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select ceil(count(*) / :pagesize) as totalPages from article", nativeQuery = true)
    public int selectTotalPages(@Param("pagesize") int pageSize);

    @Query(value = "select article_id, user_id, title, contents, created_at, like_count, view_count, updated_at " +
            "from article order by article_id desc limit :pagesize offset :offset", nativeQuery = true)
    public List<Article> selectAllArticles(@Param("pagesize") int pageSize, @Param("offset") int offset);

    Optional<Article> findByArticleId(Long articleId);

    @Query(value = "select ceil(count(*) / :pagesize) as totalPages from article where title like :query", nativeQuery = true)
    public int searchPagesByTitle(@Param("pagesize") int pageSize, @Param("query") String query);

    @Query(value = "select article_id, user_id, title, contents, created_at, like_count, view_count, updated_at " +
            "from article where title like :query order by article_id desc limit :pagesize offset :offset", nativeQuery = true)
    public List<Article> searchBoardsByTitle(@Param("pagesize") int pageSize, @Param("offset") int offset, @Param("query") String query);

    @Query(value = "select ceil(count(*) / :pagesize) as totalPages from article where contents like :query", nativeQuery = true)
    public int searchPagesByContents(@Param("pagesize") int pageSize, @Param("query") String query);

    @Query(value = "select article_id, user_id, title, contents, created_at, like_count, view_count, updated_at " +
            "from article where contents like :query order by article_id desc limit :pagesize offset :offset", nativeQuery = true)
    public List<Article> searchBoardsByContents(@Param("pagesize") int pageSize, @Param("offset") int offset, @Param("query") String query);
}
