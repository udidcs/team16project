package com.example.team16project.controller.reply;

import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.service.article.ArticleServiceImpl;
import com.example.team16project.service.reply.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Controller // RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ArticleServiceImpl articleService;
    private final ReplyServiceImpl replyService;

    @PostMapping("/reply")
    public ResponseEntity<String> createReply(@RequestBody ReplyCreateForm form, Principal principal)  {
        try {
            replyService.saveReply(form, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("등록 완료되었습니다.");
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
