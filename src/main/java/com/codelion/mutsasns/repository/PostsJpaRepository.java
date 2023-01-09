package com.codelion.mutsasns.repository;

import com.codelion.mutsasns.domain.posts.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsJpaRepository extends JpaRepository<Posts, Long> {
    Page<Posts> findByUsersId(Long userId, Pageable pageable);
}
