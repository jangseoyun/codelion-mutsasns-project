package com.codelion.mutsasns.repository;

import com.codelion.mutsasns.domain.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsJpaRepository extends JpaRepository<Posts, Long> {
}
