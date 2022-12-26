package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.posts.dto.*;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.MutsaAppException;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostsService {
    private final PostsJpaRepository postsJpaRepository;
    private final UserService userService;

    /*----- 게시물 전체 조회 (Paging)-----*/
    @Transactional(readOnly = true)
    public PostsPageResponse getPostALl(Pageable pageable) {
        Page<Posts> postsPage = postsJpaRepository.findAll(pageable);
        List<PostsDTO> postsDTOList = postsPage.stream()
                .map(posts -> PostsCreateFactory.of(posts))
                .collect(Collectors.toList());
        return PostsCreateFactory.newPostsPageResponse(postsDTOList, postsPage);
    }

    /*----- 요청 게시물 단건 조회 -----*/
    @Transactional(readOnly = true)
    public PostsDTO getPostsOne(Long postsId) {
        Optional<Posts> getPostsOne = postsJpaRepository.findById(postsId);
        return PostsCreateFactory.of(getPostsOne.get());
    }

    /*----- 회원 제한 게시물 등록 -----*/
    public PostsResponse addPosts(PostsAddRequest postsAddRequest, String loginUserName) {
        Users loginUser = userService.getUserByUserName(loginUserName);
        Posts posts = PostsCreateFactory.of(postsAddRequest, loginUser);
        Long resultPostId = postsJpaRepository.save(posts).getId();
        // TODO: 저장 안됐을 때 예외처리
        PostsResponse addResponse = PostsCreateFactory.of(resultPostId);
        return addResponse;
    }

    /*----- 요청 게시물 수정(작성자, ADMIN) -----*/
    public PostsResponse postsModifyByWriter(Long postsIdRequest, String loginUsername, PostsModifyInfo postsModifyInfo) {
        Long loginUserId = userService.getUserByUserName(loginUsername).getId();

        Posts getPosts = postsJpaRepository.findById(postsIdRequest)
                .filter(posts -> posts.getUsers().getId() == loginUserId)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.INVALID_PERMISSION, "사용자가 권한이 없습니다."));
        getPosts.postsEdit(postsModifyInfo);

        Posts editPostsResult = postsJpaRepository.save(getPosts);
        return PostsCreateFactory.newPostsResponse(editPostsResult);
    }

    /*----- 요청 게시물 삭제(작성자, ADMIN) -----*/
    public PostsResponse postsDeleteByWriter(Long postsIdRequest, String loginUsername) {
        Long loginUserId = userService.getUserByUserName(loginUsername).getId();

        postsJpaRepository.findById(postsIdRequest)
                .filter(posts -> posts.getUsers().getId() == loginUserId)
                .ifPresentOrElse(
                        posts -> postsJpaRepository.deleteById(posts.getId()),
                        () -> new MutsaAppException(ErrorCode.INVALID_PERMISSION, "사용자가 권한이 없습니다.")
                );

        return PostsCreateFactory.newPostsResponse(postsIdRequest);
    }
}
