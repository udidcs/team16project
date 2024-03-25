package com.example.team16project.domain.tag;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;

@Entity
public class Tag {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    Article article;
    String name;
}
