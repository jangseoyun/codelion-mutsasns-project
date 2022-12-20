package com.codelion.mutsasns.domain.post.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Posts(String body, String title) {
        this.body = body;
        this.title = title;
    }
}
