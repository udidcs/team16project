package com.example.team16project.dto.reply.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ReplyCreateForm {

    private Long articleId;

    @NotEmpty(message = "댓글 내용을 입력해주세요")
    private String comments;
    // 입력 내용








}
