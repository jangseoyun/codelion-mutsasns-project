package com.codelion.mutsasns.repository;

import com.codelion.mutsasns.domain.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsJpaRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostsId(Long postId, Pageable pageable);
}
