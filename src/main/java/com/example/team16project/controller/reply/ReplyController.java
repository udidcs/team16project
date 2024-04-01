package com.example.team16project.controller.reply;

import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyDeleteRequest;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.service.reply.ReplyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;

@Controller // RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @ResponseBody
    @PostMapping("/reply") // url 생각 // reply 등록 //ResponseBody 사용 여부
    public void createReply(@RequestBody ReplyCreateForm form, Principal principal) throws AuthenticationException {

        replyService.saveReply(form, principal);
    }

    @ResponseBody
    @PutMapping("/reply/{replyId}")
    public void updateReply(@PathVariable("replyId") Long replyId, Principal principal) throws AuthenticationException{

        replyService.updateReply(replyId, principal);
    }


    @ResponseBody
    @DeleteMapping("/reply/{replyId}")
    public void deleteReply(@PathVariable("replyId") Long replyId, Principal principal) throws AuthenticationException {

        replyService.deleteReply(replyId, principal);

    }


}
