package com.codelion.mutsasns.domain.like.entity;

import com.codelion.mutsasns.config.BaseEntity;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE t_like SET deleted_at = current_timestamp where like_id = ?")
@Table(name = "t_like")
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @CreatedDate
    @Column(name = "deleted_at", updatable = false)
    private LocalDateTime deletedAt;

    @Builder(builderMethodName = "insert_like")
    public Likes(Posts posts, Users users) {
        this.posts = posts;
        this.users = users;
    }

    public void updateLikeDeletedAt() {
        this.deletedAt = null;
    }

}
