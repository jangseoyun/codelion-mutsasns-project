package com.codelion.mutsasns.domain.posts.entity;

import com.codelion.mutsasns.config.BaseEntity;
import com.codelion.mutsasns.domain.posts.dto.PostsModifyInfo;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_post")
public class Posts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @Column(name = "body")
    private String body;
    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    public Posts(String body, String title, Users user) {
        this.body = body;
        this.title = title;
        this.users = user;
    }

    /* 게시물 수정 */
    public void postsEdit(PostsModifyInfo postsModifyInfo) {
        this.title = postsModifyInfo.getTitle();
        this.body = postsModifyInfo.getBody();
    }

}
