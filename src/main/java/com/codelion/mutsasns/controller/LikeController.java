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

    @PostMapping("/{postId}/likes")
    public Response<LikeUpdateResponse> likePost(@PathVariable("postId") Long postId, Authentication authentication) {
        String loginUserName = authentication.getName();
        LikeUpdateResponse likeUpdateResponse = likeService.postLike(postId, loginUserName);
        return Response.success(likeUpdateResponse);
    }

    @GetMapping("/{postsId}/likes")
    public Response postLikeCount(@PathVariable("postsId") Long postsId) {
        Long postLikeCount = likeService.getPostLikeCount(postsId);
        return Response.success(postLikeCount);
    }

}
