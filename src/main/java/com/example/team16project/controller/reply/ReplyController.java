package com.example.team16project.controller.reply;

import com.example.team16project.dto.reply.request.ReReplyCreateForm;
import com.example.team16project.dto.reply.request.ReReplyUpdateRequest;
import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.service.reply.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller // RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @ResponseBody // ResponseBody는 지워도 괜찮을 거 같습니다.
    @PostMapping("/reply")
    public ResponseEntity<String> createReply(@RequestBody ReplyCreateForm form, Principal principal)  {

        try {
            replyService.saveReply(form, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            // 400 Error
        }

        // 로그인 안했을 경우 HTTP 상태코드가 200 나오는 것을 고칠 수 있는지는 알아봐야 할 듯
        return ResponseEntity.status(HttpStatus.CREATED).body("등록 완료되었습니다.");
    }

    @ResponseBody
    @PostMapping("/reReply")
    public ResponseEntity<String> createReReply(@RequestBody ReReplyCreateForm form, Principal principal){

        try {
            replyService.saveReReply(form, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            // 400 Error
        }

        // 로그인 안했을 경우 HTTP 상태코드가 200 나오는 것을 고칠 수 있는지는 알아봐야 할 듯

        return ResponseEntity.status(HttpStatus.CREATED).body("등록 완료되었습니다.");

    }

    @ResponseBody
    @PutMapping("/reply")
    public ResponseEntity<String> updateReply(@RequestBody ReplyUpdateRequest request, Principal principal) {

        try {
            replyService.updateReply(request, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            // 403 Error
        }
        return ResponseEntity.status(HttpStatus.OK).body("수정 완료되었습니다");
    }

    @ResponseBody
    @PutMapping("/reReply")
    public ResponseEntity<String> updateReReply(@RequestBody ReReplyUpdateRequest request, Principal principal) {

        try {
            replyService.updateReReply(request, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            // 403 Error
        }
        return ResponseEntity.status(HttpStatus.OK).body("수정 완료되었습니다");
    }

    @ResponseBody
    @DeleteMapping("/reply/{replyId}")
    public ResponseEntity<String> deleteReply(@PathVariable("replyId") Long replyId, Principal principal) {


        try {
            replyService.deleteReply(replyId, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            // 403 Error
        }
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료되었습니다");
    }

    @ResponseBody
    @DeleteMapping("/reReply/{replyId}")
    public ResponseEntity<String> deleteReReply(@PathVariable("replyId") Long replyId, Principal principal) {

        try {
            replyService.deleteReReply(replyId, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            // 403 Error
        }
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료되었습니다");
    }
}
