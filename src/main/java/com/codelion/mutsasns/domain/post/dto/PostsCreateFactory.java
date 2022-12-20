package com.codelion.mutsasns.domain.post.dto;

import com.codelion.mutsasns.domain.post.entity.Posts;

public class PostsCreateFactory {

    /* Posts Entity 변환 */
    public static Posts of(PostsAddRequest postsAddRequest, String loginUserName) {
        return new Posts(
                postsAddRequest.getBody()
                , postsAddRequest.getTitle());
    }

    /* Posts Response DTO 변환 */
    public static PostsAddResponse of(Long resultPostId) {
        return new PostsAddResponse(
                resultPostId
                , "포스트 등록 완료");
    }

}
