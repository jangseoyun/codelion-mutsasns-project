package com.codelion.mutsasns.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteResponse {
    private String message;
    private Long id;
}
