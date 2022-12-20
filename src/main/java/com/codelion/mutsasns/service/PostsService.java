package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.post.dto.PostsAddRequest;
import com.codelion.mutsasns.domain.post.dto.PostsAddResponse;
import com.codelion.mutsasns.domain.post.dto.PostsCreateFactory;
import com.codelion.mutsasns.domain.post.entity.Posts;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsJpaRepository postsJpaRepository;

    /*----- 회원 제한 게시물 등록 -----*/
    public PostsAddResponse addPosts(PostsAddRequest postsAddRequest, String loginUserName) {
        Posts posts = PostsCreateFactory.of(postsAddRequest, loginUserName);
        Long resultPostId = postsJpaRepository.save(posts).getId();
        //TODO: 저장 안됐을 때 예외처리
        PostsAddResponse addResponse = PostsCreateFactory.of(resultPostId);
        return addResponse;
    }
}
