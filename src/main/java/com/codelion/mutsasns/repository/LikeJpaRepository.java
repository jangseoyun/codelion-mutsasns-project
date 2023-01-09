package com.codelion.mutsasns.repository;

import com.codelion.mutsasns.domain.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeJpaRepository extends JpaRepository<Likes, Long> {
    @Query(value = "select likes from Likes likes where likes.posts.id = :postId and likes.users.id = :userId")
    Optional<Likes> checkUserLike(@Param("postId") Long postId, @Param("userId") Long userId);

    @Query(value = "select count(likes) from Likes likes where likes.posts.id = :postsId")
    Long postLikeCount(@Param("postsId") Long postsId);
}
