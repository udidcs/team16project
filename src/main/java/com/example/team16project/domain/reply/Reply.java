package com.example.team16project.domain.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;


@Builder
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
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

    @Column(name = "comments", length = 1500, nullable = false)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Reply reply;

}
