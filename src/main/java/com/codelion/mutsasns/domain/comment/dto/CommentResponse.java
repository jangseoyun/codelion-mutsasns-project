package com.codelion.mutsasns.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;
    private String createAt;

    @Builder(builderMethodName = "toResponseDto")
    public CommentResponse(Long id, String comment, String userName, Long postId, String createAt) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.postId = postId;
        this.createAt = createAt;
    }
}
