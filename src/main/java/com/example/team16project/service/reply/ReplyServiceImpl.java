package com.example.team16project.service.reply;

import com.example.team16project.domain.article.Article;
import com.example.team16project.domain.reply.Reply;
import com.example.team16project.domain.user.User;
import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.repository.article.ArticleRepository;
import com.example.team16project.repository.reply.ReplyRepository;
import com.example.team16project.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;



    // 댓글 생성 로직

    @Transactional
    @Override
    public void saveReply(ReplyCreateForm form, Principal principal) throws AuthenticationException {

        // principal.getName(); // id(Email)를 가져옴

        // principal 검증 로직 추가 -> 로그인이 되었는지
        if(principal == null){
            throw new AuthenticationException("로그인을 해야 댓글을 작성할 수 있습니다."); // 500 Error
        }
        User user = userRepository.findByEmail(principal.getName())  //principal.getName()을 통해 user의 email을 가져옴
                .orElseThrow(IllegalArgumentException::new);

        Article article = articleRepository.findByArticleId(form.getArticleId())
                .orElseThrow(IllegalArgumentException::new);

        replyRepository.save(new Reply(article, user, form.getComments()));
    }


    // 댓글 수정 로직


    // 댓글 삭제 로직
    @Transactional
    @Override
    public void deleteReply(Long replyId, Principal principal) throws AuthenticationException {

        // principal 검증 로직 추가 -> 로그인이 되었는지
        if(principal == null){
            throw new AuthenticationException("댓글을 작성한 작성자만 삭제할 수 있습니다."); // 500 Error
        }

        Reply reply = replyRepository.findByReplyId(replyId)
                .orElseThrow(IllegalArgumentException::new);

        // 댓글을 쓴 사람만이 글을 지울 수 있게 하는 로직 추가
        if(!principal.getName().equals(reply.getUser().getEmail())){
            throw new AccessDeniedException("댓글을 작성한 작성자만 삭제할 수 있습니다."); // 403 Error
        }

        replyRepository.delete(reply);

    }

    @Transactional
    @Override
    public void updateReply(ReplyUpdateRequest request, Principal principal) throws AuthenticationException{

        // principal 검증 로직 추가 -> 로그인이 되었는지
        if(principal == null){
            throw new AuthenticationException("로그인이 필요합니다."); // 500 Error
        }

        Reply reply = replyRepository.findByReplyId(request.getReplyId())
                .orElseThrow(IllegalArgumentException::new);

        // 댓글을 쓴 사람만이 글을 지울 수 있게 하는 로직 추가
        if(!principal.getName().equals(reply.getUser().getEmail())){
            throw new AccessDeniedException("댓글을 작성한 작성자만 삭제할 수 있습니다."); // 403 Error
        }

        reply.updateComments(request.getComments());
        // 변경감지(dirty check) 작동하여 comments field 변경 후 따로 저장해 줄 필요 업음

    }
}
