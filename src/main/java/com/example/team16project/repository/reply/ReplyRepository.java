package com.example.team16project.repository.reply;

import com.example.team16project.domain.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByArticleArticleId(Long articleId);
}
