package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.alarm.dto.AlarmType;
import com.codelion.mutsasns.domain.alarm.entity.Alarm;
import com.codelion.mutsasns.domain.comment.dto.*;
import com.codelion.mutsasns.domain.comment.entity.Comment;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.MutsaAppException;
import com.codelion.mutsasns.repository.AlarmJpaRepository;
import com.codelion.mutsasns.repository.CommentsJpaRepository;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import com.codelion.mutsasns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentsJpaRepository commentsJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PostsJpaRepository postsJpaRepository;
    private final AlarmJpaRepository alarmJpaRepository;

    /*------ 댓글 작성 -----*/
    public CommentResponse addCommentByUser(Long postsId, String loginUserName, CommentCreateRequest commentCreateRequest) {
        //TODO: user가 있으면 post조회 하도록 stream
        Users loginUser = userJpaRepository.findByUserName(loginUserName)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.USERNAME_NOT_FOUND, "등록된 유저가 없습니다"));
        //post
        Posts findPost = postsJpaRepository.findById(postsId)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.POST_NOT_FOUND, "해당 게시물이 존재하지 않습니다"));

        Comment comment = CommentCreateFactory.of(loginUser, findPost, commentCreateRequest);
        Comment commentResult = commentsJpaRepository.save(comment);
        //알람 등록
        insertCommendAlarm(loginUser, postsId, AlarmType.NEW_COMMENT_ON_POST);
        return CommentCreateFactory.from(commentResult);
    }

    /*------ 댓글 수정: 권한(댓글 작성한 user)-----*/
    public CommentModifyResponse userCheckAndModify(String loginUserName, Long commentId, CommentModifyRequest commentModifyRequest) {
        //작성자 check
        Comment getComment = commentsJpaRepository.findById(commentId)
                .filter(comment -> comment.getUsers().getUserName().equals(loginUserName))
                .orElseThrow(() -> new MutsaAppException(ErrorCode.INVALID_PERMISSION, "수정 권한이 없습니다"));

        //수정
        getComment.modifyComment(commentModifyRequest.getComment());
        Comment modifyCommentResult = commentsJpaRepository.save(getComment);
        return CommentCreateFactory.newModifyResponse(modifyCommentResult);
    }

    /*------ 댓글 삭제: 권한(댓글 작성한 user)-----*/
    public CommentDeleteResponse userCheckAndDelete(Long commentId, String loginUserName) {
        commentsJpaRepository.findById(commentId)
                .filter(comment -> comment.getUsers().getUserName().equals(loginUserName))
                .ifPresentOrElse(
                        comment -> commentsJpaRepository.deleteById(commentId),
                        () -> new MutsaAppException(ErrorCode.INVALID_PERMISSION, "삭제 권한이 없습니다")
                );
        return CommentCreateFactory.newDeleteResponse(commentId);
    }

    /*------ 해당 포스트의 댓글만 조회 -----*/
    @Transactional(readOnly = true)
    public CommentListPageResponse selectCommentList(Pageable pageable, Long postId) {
        Page<Comment> commentPage = commentsJpaRepository.findByPostsId(postId, pageable);
        List<CommentResponse> commentResponseList = commentPage.stream()
                .map(comment -> CommentCreateFactory.from(comment))
                .collect(Collectors.toList());
        return CommentCreateFactory.of(commentResponseList, commentPage);
    }

    /*------ 새 포스트의 알람 등록 -----*/
    private void insertCommendAlarm(Users user, Long postId, AlarmType alarmType) {
        Alarm alarm = CommentCreateFactory.of(user, postId, alarmType);
        alarmJpaRepository.save(alarm);
    }
}
