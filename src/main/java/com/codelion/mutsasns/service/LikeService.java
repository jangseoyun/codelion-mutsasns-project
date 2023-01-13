package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.like.dto.LikeCreateFactory;
import com.codelion.mutsasns.domain.like.dto.LikeUpdateResponse;
import com.codelion.mutsasns.domain.like.entity.Likes;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.MutsaAppException;
import com.codelion.mutsasns.repository.LikeJpaRepository;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import com.codelion.mutsasns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final LikeJpaRepository likeJpaRepository;
    private final PostsJpaRepository postsJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public LikeUpdateResponse postLike(Long postId, String loginUserName) {
        //post 존재 확인
        Posts getPost = postsJpaRepository.findById(postId)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));
        //user 확인
        Users getUser = userJpaRepository.findByUserName(loginUserName)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
        //like 상태 확인
        Optional<Likes> getUserLikeOfPost = likeJpaRepository.checkUserLike(postId, getUser.getId());

        if (getUserLikeOfPost.isEmpty()) {
            likeJpaRepository.save(LikeCreateFactory.of(getPost, getUser));
        }

        if (getUserLikeOfPost.isPresent()) {
            getUserLikeOfPost
                    .filter(likes -> likes.getDeletedAt() == null)
                    .ifPresentOrElse(
                            likes -> likeJpaRepository.deleteById(likes.getId()),
                            () -> getUserLikeOfPost.get().updateLikeDeletedAt()
                    );
        }
        return LikeCreateFactory.from(getUserLikeOfPost.get());
    }

    public Long getPostLikeCount(Long postsId) {
        return likeJpaRepository.postLikeCount(postsId);
    }

    /*------ 좋아요 알람 등록 -----*/


}
