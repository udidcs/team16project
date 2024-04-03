package com.example.team16project.service.reply;

import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.dto.reply.response.EditReplyViewResponse;
import com.example.team16project.dto.reply.response.ReplyDto;

import javax.naming.AuthenticationException;
import java.security.Principal;
import java.util.List;

public interface ReplyService {

    // 댓글 생성 로직
    void saveReply(ReplyCreateForm form, Principal principal) throws AuthenticationException;

    // 댓글 수정 로직
    void updateReply(ReplyUpdateRequest request, Principal principal) throws AuthenticationException;

    // 댓글 삭제 로직
    void deleteReply(Long replyId, Principal principal) throws AuthenticationException;

    void checkConditionToMoveToEditReplyPage(Long replyId, Principal principal) throws AuthenticationException;

    EditReplyViewResponse makeReplyViewResponse(Long replyId);
    
    public List<ReplyDto> getAllReplysOnArticle(Long articleId);

}
