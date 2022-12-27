package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.posts.dto.*;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.ErrorResult;
import com.codelion.mutsasns.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 멋쟁이사자처럼 첫번째 미션 Post
 * @author jang.seoyun (아이다섯이둘)
 * @version 0.1
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostsService postsService;

    /**
     * 게시물 전체 조회 (paging)
     * @param pageable 게시물 최신 순으로 20개씩 표시
     * @return posts, 페이지 정보
     */
    @GetMapping("")
    public Response<PostsPageResponse> getPostsAll(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return Response.success(postsService.getPostALl(pageable));
    }

    /**
     * 요청 게시물 단건 상세조회
     * @param postsId
     * @return id, 제목, 내용, 작성자, 작성날짜, 수정날짜
     */
    @GetMapping("{postsId}")
    public Response<PostsDTO> getPostsOne(@PathVariable("postsId") Long postsId) {
        PostsDTO getPostsOne = postsService.getPostsOne(postsId);
        return Response.success(getPostsOne);
    }

    /**
     * 회원 제한 게시물 등록
     * @param postsAddRequest
     * @param authentication
     * @return 등록된 postId
     */
    @PostMapping("")
    public Response addPosts(@RequestBody @Valid PostsAddRequest postsAddRequest, Authentication authentication) {
        if (!authentication.isAuthenticated())
            return Response.error("ERROR", new ErrorResult(ErrorCode.USERNAME_NOT_FOUND, "회원만 접근 가능"));

        PostsResponse postsAddResponse = postsService.addPosts(postsAddRequest, authentication.getName());
        return Response.success(postsAddResponse);
    }

    /**
     * 요청 게시물 수정(권한: 작성자, ADMIN)
     * @param postsIdRequest 수정할 postId
     * @param postModifyInfo 수정 내용 (제목, 내용)
     * @param authentication 로그인 인증
     * @return 수정완료 메세지, 수정된 postId
     */
    @PutMapping("{id}")
    public Response<PostsResponse> postsModify(@PathVariable("id") Long postsIdRequest
            , @RequestBody @Valid PostsModifyInfo postModifyInfo
            , Authentication authentication) {

        PostsResponse modifyResponse = postsService.postsModifyByWriter(postsIdRequest, authentication.getName(), postModifyInfo);
        return Response.success(modifyResponse);
    }

    /**
     * 요청 게시물 삭제(권한: 작성자, ADMIN)
     * @param postsIdRequest 삭제할 postId
     * @param authentication 로그인 인증
     * @return 삭제완료 메세지, 삭제된 postId
     */
    @DeleteMapping("{id}")
    public Response<PostsResponse> postsDelete(@PathVariable("id") Long postsIdRequest, Authentication authentication) {
        PostsResponse deleteResponse = postsService.postsDeleteByWriter(postsIdRequest, authentication.getName());
        return Response.success(deleteResponse);
    }

}
