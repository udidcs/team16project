package com.example.team16project.domain.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;

@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer replyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;

    String comments;

    @ManyToOne
    @JoinColumn(name = "parentId")
    private Reply reply;

}
