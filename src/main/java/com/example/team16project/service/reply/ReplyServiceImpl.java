package com.example.team16project.service.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.reply.Reply;
import com.example.team16project.domain.user.User;
import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyDeleteRequest;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.dto.reply.response.ReplyDto;
import com.example.team16project.repository.article.ArticleRepository;
import com.example.team16project.repository.reply.ReplyRepository;
import com.example.team16project.repository.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void saveReply(ReplyCreateForm form, Principal principal) {

        // principal.getName(); // id(Email)를 가져옴

        // To-do : ReplyCreateForm 수정
        // article 은 html 쪽에서 받아오는걸로 (fetch 활용 / form을 활용하지 않을시에는)
        // form에서 data를 받아오는 것도 가능 (html코드에서 form을 활용한다면)

        User user = userRepository.findByEmail(principal.getName())  //principal.getName()을 통해 user의 email을 가져옴
                .orElseThrow(IllegalArgumentException::new);

        Article article = articleRepository.findByArticleId(form.getArticleId())
                .orElseThrow(IllegalArgumentException::new); // article에 계속 null 값이 들어감 ArticleId가 안맞음

        replyRepository.save(new Reply(article, user, form.getComments()));
    }

    @Transactional
    @Override
    public List<ReplyDto> getAllReplysOnArticle(Long articleId) {
        List<Reply> byArticleArticleIdAndReplyReplyId = replyRepository.findByArticleArticleIdAndReplyReplyId(articleId, null);
        List<ReplyDto> collect = byArticleArticleIdAndReplyReplyId.stream().map(reply -> ReplyDto.toDto(reply,
                replyRepository.findByReplyReplyId(reply.getReplyId()).stream().map(reply1 -> new ReplyDto(reply1.getReplyId(), reply1.getUser().getNickname(), reply1.getComments(), null))
                        .collect(Collectors.toList()))).collect(Collectors.toList());
        return collect;
    }
}
