package com.example.team16project.repository.article;

import com.example.team16project.domain.article.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "select ceil(count(*) / :pagesize) as totalPages from article", nativeQuery = true)
    public int selectTotalPages(@Param("pagesize") int pageSize);

    @Query(value = "select article_id, user_id, title, contents, created_at, like_count, view_count, updated_at " +
            "from article order by article_id desc limit :pagesize offset :offset", nativeQuery = true)
    public List<Article> selectAllCoteBaords(@Param("pagesize") int pageSize, @Param("offset") int offset);

}
