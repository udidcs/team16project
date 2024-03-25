package com.example.team16project.domain.tag;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Tag {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    Article article;

    @Column(length = 20, nullable = false)
    String name;
}
