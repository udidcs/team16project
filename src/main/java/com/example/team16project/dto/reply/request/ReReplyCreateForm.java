package com.example.team16project.dto.reply.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ReReplyCreateForm {

    private Long articleId;

    private Long parentReplyId;

    @NotEmpty(message = "댓글 내용을 입력해주세요")
    private String reComments;
    // 입력 내용


}
