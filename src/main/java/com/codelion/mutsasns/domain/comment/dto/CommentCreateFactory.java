package com.codelion.mutsasns.domain.comment.dto;

import com.codelion.mutsasns.domain.alarm.dto.AlarmType;
import com.codelion.mutsasns.domain.alarm.entity.Alarm;
import com.codelion.mutsasns.domain.comment.entity.Comment;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import org.springframework.data.domain.Page;

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

    public static Alarm of(Users user, Long postId, AlarmType alarmType) {
        return Alarm.builder()
                .users(user)
                .alarmType(alarmType)
                .fromUserId(user.getId())
                .targetId(postId)
                .text(setAlarmText(alarmType))
                .build();
    }

    private static String setAlarmText(AlarmType alarmType) {
        if (alarmType.equals(AlarmType.NEW_COMMENT_ON_POST)) {
            return "new comment!";
        }
        return "new like!";
    }
}
