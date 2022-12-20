package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.post.dto.PostsAddRequest;
import com.codelion.mutsasns.domain.post.dto.PostsAddResponse;
import com.codelion.mutsasns.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostsService postsService;

    /*----- 회원 제한 게시물 등록 -----*/
    @PostMapping("")
    public Response addPosts(@RequestBody PostsAddRequest postsAddRequest, Authentication authentication) {
        if (!authentication.isAuthenticated()) return Response.error("사용자 권한이 필요합니다, 로그인을 해주세요!");

        PostsAddResponse postsAddResponse = postsService.addPosts(postsAddRequest, authentication.getName());
        return Response.success(postsAddResponse);
    }

}
