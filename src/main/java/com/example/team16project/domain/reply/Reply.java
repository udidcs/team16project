package com.example.team16project.domain.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;


@Builder
@Entity
@Getter
@ToString
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply reply;

    public Reply(Article article, User user, String comments) {
        if(comments==null||comments.isBlank()){
            throw new IllegalArgumentException("댓글 내용을 입력해주세요"); //500 Error
        } // 댓글에 아무것도 작성 안하면 Error 발생
        this.article = article;
        this.user = user;
        this.comments = comments;
    }

    public void updateComments(String comments) {
        this.comments = comments;
    }
}
