package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.comment.dto.*;
import com.codelion.mutsasns.domain.comment.entity.Comment;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.MutsaAppException;
import com.codelion.mutsasns.repository.CommentsJpaRepository;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import com.codelion.mutsasns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentsJpaRepository commentsJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PostsJpaRepository postsJpaRepository;

    /*------ 댓글 작성 -----*/
    public CommentCreateResponse addCommentByUser(Long postsId, String loginUserName, CommentCreateRequest commentCreateRequest) {
        //TODO: user가 있으면 post조회 하도록 stream
        Users loginUser = userJpaRepository.findByUserName(loginUserName)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.USERNAME_NOT_FOUND, "등록된 유저가 없습니다"));
        //post
        Posts findPost = postsJpaRepository.findById(postsId)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.POST_NOT_FOUND, "해당 게시물이 존재하지 않습니다"));

        Comment comment = CommentCreateFactory.of(loginUser, findPost, commentCreateRequest);
        Comment commentResult = commentsJpaRepository.save(comment);

        return CommentCreateFactory.newCreateResponse(commentResult);
    }

    /*------ 댓글 수정: 권한(댓글 작성한 user)-----*/
    public CommentModifyResponse userCheckAndModify(String loginUserName, Long modifyPostId, Long commentId, CommentModifyRequest commentModifyRequest) {
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
    public CommentDeleteResponse userCheckAndDelete(Long postId, Long commentId, String loginUserName) {
        commentsJpaRepository.findById(commentId)
                .filter(comment -> comment.getUsers().getUserName().equals(loginUserName))
                .ifPresentOrElse(
                        comment -> commentsJpaRepository.deleteById(commentId),
                        () -> new MutsaAppException(ErrorCode.INVALID_PERMISSION, "삭제 권한이 없습니다")
                );
        return CommentCreateFactory.newDeleteResponse(commentId);
    }
}
