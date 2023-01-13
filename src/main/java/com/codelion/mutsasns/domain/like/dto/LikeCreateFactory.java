package com.codelion.mutsasns.domain.like.dto;

import com.codelion.mutsasns.domain.like.entity.Likes;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;

public class LikeCreateFactory {

    public static Likes of(Posts posts, Users users) {
        return Likes
                .insert_like()
                .posts(posts)
                .users(users)
                .build();
    }

    public static LikeUpdateResponse from(Likes updateLikes) {
        if (updateLikes.getDeletedAt() == null) {
            return new LikeUpdateResponse("좋아요를 눌렀습니다.");
        }

        return new LikeUpdateResponse("좋아요를 취소 했습니다.");
    }

}
