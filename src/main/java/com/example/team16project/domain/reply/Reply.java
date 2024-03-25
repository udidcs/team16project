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
    @JoinColumn(name = "article_id")
    Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    String comments;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Reply reply;

}
