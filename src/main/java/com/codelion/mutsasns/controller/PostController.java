package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.posts.dto.PostsAddRequest;
import com.codelion.mutsasns.domain.posts.dto.PostsAddResponse;
import com.codelion.mutsasns.domain.posts.dto.PostsDTO;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.ErrorResult;
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
        if (!authentication.isAuthenticated()) return Response.error("ERROR", new ErrorResult(ErrorCode.USERNAME_NOT_FOUND, "회원만 접근 가능"));

        PostsAddResponse postsAddResponse = postsService.addPosts(postsAddRequest, authentication.getName());
        return Response.success(postsAddResponse);
    }

    /*----- 요청 게시물 단건 조회 -----*/
    //삭제 수정
    @GetMapping("{postsId}")
    public PostsDTO getPostsOne(@PathVariable("postsId") Long postsId) {
        return postsService.getPostsOne(postsId);
    }

}
