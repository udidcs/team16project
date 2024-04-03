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

import java.security.Principal;

@Controller // RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @ResponseBody
    @PostMapping("/reply") // url 생각 // reply 등록 //ResponseBody 사용 여부
    public String createComment(@RequestBody ReplyCreateForm form, Principal principal){
        System.out.println("-------------------------------???");
        // To - do : RequestParam 대신 body로 받아주는 방식으로 Post는 body 방식으로 넘겨주는게 좋으니까
        // form에 articleId를 넣어야 함

//        // comment (댓글 내용이 등록되어있지 않다면 에러 발생
//        if(form.getComments() == null){
//            throw new IllegalArgumentException("댓글을 입력해주세요");
//        }

        replyService.saveReply(form, principal);
        return "/article/detail"; // 저장 -> 댓글 작성 화면 // 이 부분은 없어도 될 듯 ??

    }





}
