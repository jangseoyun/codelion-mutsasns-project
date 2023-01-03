package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.comment.dto.CommentCreateRequest;
import com.codelion.mutsasns.domain.comment.dto.CommentCreateResponse;
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

    @PostMapping("/{postsId}/comments")
    public Response<CommentCreateResponse> addComment(@PathVariable("postsId") Long postsId
            , @RequestBody CommentCreateRequest commentCreateRequest
            , Authentication authentication) {

        String loginUserName = authentication.getName();
        CommentCreateResponse commentCreateResponse = commentService.addCommentByUser(postsId, loginUserName, commentCreateRequest);
        return Response.success(commentCreateResponse);
    }
}
