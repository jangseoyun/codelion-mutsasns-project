package com.codelion.mutsasns.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentCreateResponse {
    private Long id;
    private String comment;
    private String userName;
    private Long postId;
    private String createAt;
    private String lastModifiedAt;

    @Builder(builderMethodName = "toResponseDto")
    public CommentCreateResponse(Long id, String comment, String userName, Long postId, String createAt, String lastModifiedAt) {
        this.id = id;
        this.comment = comment;
        this.userName = userName;
        this.postId = postId;
        this.createAt = createAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
