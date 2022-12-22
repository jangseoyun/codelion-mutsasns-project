package com.codelion.mutsasns.domain.posts.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsDTO {
    private Long id;
    private String title;
    private String body;
    private String userName;
    private String createdAt;
    private String lastModifiedAt;

    @Builder
    public PostsDTO(Long id, String title, String body, String userName, String createdAt, String lastModifiedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public PostsDTO(Long id, String title, String body, String userName, String createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userName = userName;
        this.createdAt = createdAt;
    }
}
