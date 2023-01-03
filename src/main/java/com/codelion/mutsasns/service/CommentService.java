package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.comment.dto.CommentCreateFactory;
import com.codelion.mutsasns.domain.comment.dto.CommentCreateRequest;
import com.codelion.mutsasns.domain.comment.dto.CommentCreateResponse;
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

        return CommentCreateFactory.from(commentResult);
    }
}
