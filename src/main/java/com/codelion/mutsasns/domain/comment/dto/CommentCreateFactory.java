package com.codelion.mutsasns.domain.comment.dto;

import com.codelion.mutsasns.domain.comment.entity.Comment;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;

public class CommentCreateFactory {

    public static Comment of(Users getUser, Posts getPost, CommentCreateRequest commentCreateRequest) {
        return new Comment(
                commentCreateRequest.getComment()
                , getPost
                , getUser
        );
    }

    public static CommentCreateResponse from(Comment comment) {
        return CommentCreateResponse
                .toResponseDto()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUsers().getUserName())
                .postId(comment.getPosts().getId())
                .createAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .build();
    }
}
