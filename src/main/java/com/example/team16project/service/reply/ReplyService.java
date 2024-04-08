package com.example.team16project.service.reply;

import com.example.team16project.dto.reply.request.ReReplyCreateForm;
import com.example.team16project.dto.reply.request.ReReplyUpdateRequest;
import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.dto.reply.response.ReplyDto;
import java.security.Principal;
import java.util.List;

public interface ReplyService {

    // 댓글 생성 로직
    void saveReply(ReplyCreateForm form, Principal principal);

    // 대댓글 생성 로직
    void saveReReply(ReReplyCreateForm form, Principal principal);

    long countHowManyReplies(Long articleId);

    // 댓글 수정 로직
    void updateReply(ReplyUpdateRequest request, Principal principal);

    // 대댓글 수정 로직
    void updateReReply(ReReplyUpdateRequest request, Principal principal);

    // 댓글 삭제 로직
    void deleteReply(Long replyId, Principal principal);

    // 대댓글 삭제 로직
    void deleteReReply(Long replyId, Principal principal);

    List<ReplyDto> getAllReplysOnArticle(Long articleId);

}
