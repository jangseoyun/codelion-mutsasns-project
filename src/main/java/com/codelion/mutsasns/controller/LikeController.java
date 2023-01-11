package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.like.dto.LikeUpdateResponse;
import com.codelion.mutsasns.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class LikeController {
    private final LikeService likeService;

    /**
     * 포스트 좋아요 등록 및 취소
     * @param postId
     * @param authentication
     * @return
     */
    @PostMapping("/{postId}/likes")
    public Response<LikeUpdateResponse> likePost(@PathVariable("postId") Long postId, Authentication authentication) {
        String loginUserName = authentication.getName();
        LikeUpdateResponse likeUpdateResponse = likeService.postLike(postId, loginUserName);
        return Response.success(likeUpdateResponse);
    }

    /**
     * 해당 게시글 좋아요 갯수
     * @param postsId
     * @return 해당 게시글 좋아요 갯수
     */
    @GetMapping("/{postsId}/likes")
    public Response postLikeCount(@PathVariable("postsId") Long postsId) {
        Long postLikeCount = likeService. getPostLikeCount(postsId);
        return Response.success(postLikeCount);
    }

}
