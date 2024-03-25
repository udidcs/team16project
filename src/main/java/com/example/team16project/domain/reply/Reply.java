package com.example.team16project.domain.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long replyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    User user;

    @Column(length = 1500, nullable = false)
    String comments;

    @ManyToOne
    @JoinColumn(name = "parentId", nullable = false)
    @ColumnDefault(value = "0")
    private Reply reply;

}
