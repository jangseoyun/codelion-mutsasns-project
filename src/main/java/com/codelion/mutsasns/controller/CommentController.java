package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.comment.dto.*;
import com.codelion.mutsasns.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class CommentController {
    private final CommentService commentService;

    /**
     * 해당 포스트 댓글 등록
     * @param postsId, 인증(권한), 댓글 내용,
     * @return CommentResponse(번호, 내용, 댓글 작성자 이름, 게시글 번호, 댓글 작성일)
     */
    @PostMapping("/{postsId}/comments")
    public Response<CommentResponse> addComment(@PathVariable("postsId") Long postsId
            , @RequestBody CommentCreateRequest commentCreateRequest
            , Authentication authentication) {

        String loginUserName = authentication.getName();
        CommentResponse commentCreateResponse = commentService.addCommentByUser(postsId, loginUserName, commentCreateRequest);
        return Response.success(commentCreateResponse);
    }

    /**
     * 댓글 수정
     * @param commentId, 인증(권한), 수정할 댓글 내용
     * @return CommentModifyResponse(id, 댓글, 댓글 작성자 이름, 게시글 번호, 댓글 작성일, 최근 댓글 수정일)
     */
    @PutMapping("/{postId}/comments/{id}")
    public Response<CommentModifyResponse> commentsModify(@PathVariable("id") Long commentId, Authentication authentication, @RequestBody CommentModifyRequest commentModifyRequest) {
        String loginUserName = authentication.getName();
        CommentModifyResponse commentModifyResponse = commentService.userCheckAndModify(loginUserName, commentId, commentModifyRequest);
        return Response.success(commentModifyResponse);
    }

    /**
     * 댓글 삭제
     * @param commentId, 인증(권한)
     * @return commentId, 삭제 메세지
     */
    @DeleteMapping("/{postsId}/comments/{id}")
    public Response<CommentDeleteResponse> commentsDelete(@PathVariable("id") Long commentId, Authentication authentication) {
        String loginUserName = authentication.getName();
        CommentDeleteResponse commentDeleteResponse = commentService.userCheckAndDelete(commentId, loginUserName);
        return Response.success(commentDeleteResponse);
    }

    /**
     * 댓글 조회(paging: size 10)
     * @param postId, pageable
     * @return content list, 페이징 정보
     */
    @GetMapping("{postId}/comments")
    public Response<CommentListPageResponse> selectCommentList(@PathVariable("postId") Long postId,
                                                               @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        CommentListPageResponse commentListPageResponse = commentService.selectCommentList(pageable, postId);
        return Response.success(commentListPageResponse);
    }
}
