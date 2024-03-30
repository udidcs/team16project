package com.example.team16project.domain.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Getter
@Table(name = "reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Reply {

    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comments", nullable = false, length = 1000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Reply reply;


    public Reply(Article article, User user, String comments) {
        this.article = article;
        this.user = user;
        this.comments = comments;
    }


}
