package com.codelion.mutsasns.domain.posts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostsModifyInfo {
    private String title;
    private String body;
}
