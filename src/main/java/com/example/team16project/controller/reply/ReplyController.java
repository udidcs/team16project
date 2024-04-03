package com.example.team16project.controller.reply;

import com.example.team16project.domain.user.User;
import com.example.team16project.dto.article.response.ArticleDto;
import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.service.article.ArticleServiceImpl;
import com.example.team16project.service.reply.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Controller // RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ArticleServiceImpl articleService;

    private final ReplyServiceImpl replyService;

    @ResponseBody
    @PostMapping("/reply")
    public void createReply(@RequestBody ReplyCreateForm form, Principal principal) throws AuthenticationException {

        replyService.saveReply(form, principal);
    }

    @ResponseBody
    @PutMapping("/reply")
    public void updateReply(@RequestBody ReplyUpdateRequest request, Principal principal) throws AuthenticationException{

        replyService.updateReply(request, principal);
    }


    @ResponseBody
    @DeleteMapping("/reply/{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId, Principal principal) throws AuthenticationException {

        replyService.deleteReply(replyId, principal);

    }


}
