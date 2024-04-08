package com.example.team16project.controller.reply;

import com.example.team16project.dto.reply.request.ReReplyCreateForm;
import com.example.team16project.dto.reply.request.ReReplyUpdateRequest;
import com.example.team16project.dto.reply.request.ReplyCreateForm;
import com.example.team16project.dto.reply.request.ReplyUpdateRequest;
import com.example.team16project.service.reply.ReplyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Tag(name = "댓글 CRUD")
@Controller // RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @ResponseBody
    @PostMapping("/reply")
    @Operation(summary = "새로운 댓글 생성", description = "댓글 내용을 입력받아 새로운 댓글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 완료되었습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "200", description = "로그인이 필요합니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "댓글 내용을 입력해주세요", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "articleId", description = "게시글 Id", example = "1", content = @Content()),
            @Parameter(name = "comments", description = "댓글 내용", example = "댓글입니다.")
    })
    public ResponseEntity<String> createReply(@RequestBody ReplyCreateForm form, Principal principal)  {

        try {
            replyService.saveReply(form, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            // 400 Error
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("등록 완료되었습니다.");
    }

    @ResponseBody
    @PostMapping("/reReply")
    @Operation(summary = "새로운 대댓글 생성", description = "대댓글 내용을 입력받아 새로운 대댓글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "등록 완료되었습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "200", description = "로그인이 필요합니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "댓글 내용을 입력해주세요", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "articleId", description = "게시글 Id", example = "1"),
            @Parameter(name = "parentReplyId", description = "부모 댓글 Id", example = "1"),
            @Parameter(name = "reComments", description = "대댓글 내용", example = "대댓글입니다.")
    })
    public ResponseEntity<String> createReReply(@RequestBody ReReplyCreateForm form, Principal principal){

        try {
            replyService.saveReReply(form, principal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            // 400 Error
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("등록 완료되었습니다.");
    }

    @ResponseBody
    @PutMapping("/reply")
    @Operation(summary = "댓글 내용 수정", description = "수정 내용을 입력받아 댓글 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 완료되었습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "로그인이 필요합니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "댓글을 작성한 작성자만 수정할 수 있습니다.", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "replyId", description = "댓글 Id", example = "1"),
            @Parameter(name = "comments", description = "수정할 댓글 내용", example = "수정할 댓글내용입니다.")
    })
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
    @Operation(summary = "대댓글 내용 수정", description = "수정 내용을 입력받아 대댓글 내용을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 완료되었습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "로그인이 필요합니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "댓글을 작성한 작성자만 수정할 수 있습니다.", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "replyId", description = "대댓글 Id", example = "1"),
            @Parameter(name = "comments", description = "수정할 대댓글 내용", example = "수정할 대댓글내용입니다.")
    })
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
    @Operation(summary = "댓글 삭제", description = "선택한 댓글을 삭제합니다. 만약 해당 댓글에 대댓글이 존재한다면 해당 댓글과 대댓글 모두 제거합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 완료되었습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "로그인이 필요합니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "댓글을 작성한 작성자만 삭제할 수 있습니다.", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "replyId", description = "댓글 Id", example = "1")
    })
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
    @Operation(summary = "대댓글 삭제", description = "선택한 대댓글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 완료되었습니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "405", description = "로그인이 필요합니다", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "댓글을 작성한 작성자만 삭제할 수 있습니다.", content = @Content(mediaType = "application/json"))
    })
    @Parameters({
            @Parameter(name = "replyId", description = "대댓글 Id", example = "1")
    })
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
