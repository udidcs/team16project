package com.example.team16project.service.reply;

import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.response.ReplyDto;

import java.security.Principal;
import java.util.List;

public interface ReplyService {
    public void saveReply(ReplyCreateForm form, Principal principal);
    public List<ReplyDto> getAllReplysOnArticle(Long articleId);
}
