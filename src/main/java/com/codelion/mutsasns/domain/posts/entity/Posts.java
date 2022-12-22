package com.codelion.mutsasns.domain.posts.entity;

import com.codelion.mutsasns.domain.posts.dto.PostsModifyInfo;
import com.codelion.mutsasns.domain.user.entity.Users;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_post")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(name = "body")
    private String body;
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @CreatedDate
    @Column(name = "created_at")
    private String createdAt;

    @LastModifiedDate
    @Column(name = "ast_modified_at")
    private String lastModifiedAt;

    public Posts(String body, String title, Users user) {
        this.body = body;
        this.title = title;
        this.users = user;
    }

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.lastModifiedAt = this.createdAt;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.lastModifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /* 게시물 수정 */
    public void postsEdit(PostsModifyInfo postsModifyInfo) {
        this.title = postsModifyInfo.getTitle();
        this.body = postsModifyInfo.getBody();
    }

}
