package com.codelion.mutsasns.domain.like.dto;

import com.codelion.mutsasns.domain.like.entity.Likes;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;

import java.time.LocalDateTime;

public class LikeCreateFactory {

    public static Likes of(Posts posts, Users users) {
        return Likes
                .insert_deletedAt()
                .posts(posts)
                .users(users)
                .build();
    }

    public static Likes of(Posts posts, Users users, LocalDateTime deleteStatus) {
        return Likes
                .remove_deletedAt()
                .posts(posts)
                .users(users)
                .deletedAt(updateDeleteAt(deleteStatus))
                .build();
    }

    public static LikeUpdateResponse from(Likes updateLikes) {
        if (updateLikes.getDeletedAt() == null) {
            return new LikeUpdateResponse("좋아요를 눌렀습니다.");
        }

        return new LikeUpdateResponse("좋아요를 취소 했습니다.");
    }

    private static LocalDateTime updateDeleteAt(LocalDateTime deletedAt) {
        return deletedAt = null;
    }

}
