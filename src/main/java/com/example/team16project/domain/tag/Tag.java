package com.example.team16project.domain.tag;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "name", nullable = false)
    private String name;
}
