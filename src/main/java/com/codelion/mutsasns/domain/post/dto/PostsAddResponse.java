package com.codelion.mutsasns.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsAddResponse {
    private Long postId;
    private String message;
}
