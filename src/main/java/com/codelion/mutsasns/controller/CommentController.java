package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.comment.dto.CommentCreateRequest;
import com.codelion.mutsasns.domain.comment.dto.CommentCreateResponse;
import com.codelion.mutsasns.domain.comment.dto.CommentModifyRequest;
import com.codelion.mutsasns.domain.comment.dto.CommentModifyResponse;
import com.codelion.mutsasns.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentController {
    private final CommentService commentService;

    /*---------- 댓글 작성 --------*/
    @PostMapping("/{postsId}/comments")
    public Response<CommentCreateResponse> addComment(@PathVariable("postsId") Long postsId
            , @RequestBody CommentCreateRequest commentCreateRequest
            , Authentication authentication) {

        String loginUserName = authentication.getName();
        CommentCreateResponse commentCreateResponse = commentService.addCommentByUser(postsId, loginUserName, commentCreateRequest);
        return Response.success(commentCreateResponse);
    }

    /*---------- 댓글 수정 --------*/
    @PutMapping("/{postId}/comments/{id}")
    public Response<CommentModifyResponse> commentsModify(@PathVariable("postId") Long postId, @PathVariable("id") Long commentId
            , Authentication authentication, @RequestBody CommentModifyRequest commentModifyRequest) {
        String loginUserName = authentication.getName();
        CommentModifyResponse commentModifyResponse = commentService.userCheckAndModify(loginUserName, postId, commentId, commentModifyRequest);
        return Response.success(commentModifyResponse);
    }

}
