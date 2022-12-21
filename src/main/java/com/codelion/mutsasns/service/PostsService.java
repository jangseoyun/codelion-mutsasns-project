package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.posts.dto.PostsAddRequest;
import com.codelion.mutsasns.domain.posts.dto.PostsAddResponse;
import com.codelion.mutsasns.domain.posts.dto.PostsCreateFactory;
import com.codelion.mutsasns.domain.posts.dto.PostsDTO;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsJpaRepository postsJpaRepository;
    private final UserService userService;

    /*----- 회원 제한 게시물 등록 -----*/
    public PostsAddResponse addPosts(PostsAddRequest postsAddRequest, String loginUserName) {
        Users loginUser = userService.getUserByUserName(loginUserName);
        Posts posts = PostsCreateFactory.of(postsAddRequest, loginUser);
        Long resultPostId = postsJpaRepository.save(posts).getId();
        //TODO: 저장 안됐을 때 예외처리
        PostsAddResponse addResponse = PostsCreateFactory.of(resultPostId);
        return addResponse;
    }

    /*----- 요청 게시물 단건 조회 -----*/
    public PostsDTO getPostsOne(Long postsId) {
        Optional<Posts> getPostsOne = postsJpaRepository.findById(postsId);
        return PostsCreateFactory.of(getPostsOne.get());
    }

}
