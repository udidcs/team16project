package com.example.team16project.dto.reply.response;

import com.example.team16project.domain.reply.Reply;
import lombok.*;
import java.util.List;


@Builder
@Data
public class ReplyDto {

    private Long replyId;
    private String nickname;
    private String comments;
    private List<ReplyDto> replys;

    public ReplyDto(Long replyId, String nickname, String comments, List<ReplyDto> replys) {
        this.replyId = replyId;
        this.nickname = nickname;
        this.comments = comments;
        this.replys = replys;
    }

    public static ReplyDto toDto(Reply reply, List<ReplyDto> byReplyReplyId) {
        return ReplyDto.builder()
                .replyId(reply.getReplyId())
                .nickname(reply.getUser().getNickname())
                .comments(reply.getComments())
                .replys(byReplyReplyId).build();

    }
}
