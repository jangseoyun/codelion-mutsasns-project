package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.posts.dto.*;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.ErrorResult;
import com.codelion.mutsasns.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostsService postsService;

    /*----- 회원 제한 게시물 등록 -----*/
    @PostMapping("")
    public Response addPosts(@RequestBody @Valid PostsAddRequest postsAddRequest, Authentication authentication) {
        if (!authentication.isAuthenticated())
            return Response.error("ERROR", new ErrorResult(ErrorCode.USERNAME_NOT_FOUND, "회원만 접근 가능"));

        PostsResponse postsAddResponse = postsService.addPosts(postsAddRequest, authentication.getName());
        return Response.success(postsAddResponse);
    }

    /*----- 요청 게시물 단건 조회 -----*/
    @GetMapping("{postsId}")
    public PostsDTO getPostsOne(@PathVariable("postsId") Long postsId) {
        return postsService.getPostsOne(postsId);
    }

    /*----- 요청 게시물 수정(작성자, ADMIN) -----*/
    @PutMapping("{id}")
    public Response<PostsResponse> postsModify(@PathVariable("id") Long postsIdRequest
            , @RequestBody @Valid PostsModifyInfo postModifyInfo
            , Authentication authentication) {

        PostsResponse modifyResponse = postsService.postsModifyByWriter(postsIdRequest, authentication.getName(), postModifyInfo);
        return Response.success(modifyResponse);
    }

    /*----- 요청 게시물 삭제(작성자, ADMIN) -----*/
    @DeleteMapping("{id}")
    public Response<PostsResponse> postsDelete(@PathVariable("id") Long postsIdRequest, Authentication authentication) {
        PostsResponse deleteResponse = postsService.postsDeleteByWriter(postsIdRequest, authentication.getName());
        return Response.success(deleteResponse);
    }
}
