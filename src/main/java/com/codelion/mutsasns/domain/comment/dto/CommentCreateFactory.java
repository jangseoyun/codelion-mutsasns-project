package com.codelion.mutsasns.domain.comment.dto;

import com.codelion.mutsasns.domain.comment.entity.Comment;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CommentCreateFactory {

    public static Comment of(Users getUser, Posts getPost, CommentCreateRequest commentCreateRequest) {
        return new Comment(
                commentCreateRequest.getComment()
                , getPost
                , getUser
        );
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse
                .toResponseDto()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUsers().getUserName())
                .postId(comment.getPosts().getId())
                .createAt(comment.getCreatedAt())
                .build();
    }

    public static CommentModifyResponse newModifyResponse(Comment comment) {
        return CommentModifyResponse
                .toModifyDto()
                .id(comment.getId())
                .comment(comment.getComment())
                .userName(comment.getUsers().getUserName())
                .postId(comment.getPosts().getId())
                .createAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .build();
    }

    public static CommentDeleteResponse newDeleteResponse(Long commentId) {
        return new CommentDeleteResponse(
                "댓글 삭제 완료"
                , commentId);
    }

    public static CommentListPageResponse of(List<CommentResponse> commentResponseList, Page<Comment> commentPage) {
        return CommentListPageResponse.builder()
                .content(commentResponseList)
                .pageable(commentPage.getPageable())
                .last(commentPage.hasNext())
                .totalPages(commentPage.getTotalPages())
                .size(commentPage.getSize())
                .number(commentPage.getNumber())
                .sort(commentPage.getSort())
                .numberOfElements(commentPage.getNumberOfElements())
                .first(commentPage.isFirst())
                .empty(commentPage.isEmpty())
                .build();
    }
}
