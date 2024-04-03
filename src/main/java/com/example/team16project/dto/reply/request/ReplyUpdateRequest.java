package com.example.team16project.dto.reply.request;

import lombok.Getter;

@Getter
public class ReplyUpdateRequest {

    private Long ReplyId; // 게시글 id

    private String comments; // 입력 내용

}
