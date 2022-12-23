package com.codelion.mutsasns.domain.posts.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsPageResponse {
    private List<PostsDTO> content;
    private PostPagingInfo pageable;
}
