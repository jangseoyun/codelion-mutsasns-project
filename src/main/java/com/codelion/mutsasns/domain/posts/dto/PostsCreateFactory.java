package com.codelion.mutsasns.domain.posts.dto;

import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;

public class PostsCreateFactory {

    /* Posts Entity 변환 */
    public static Posts of(PostsAddRequest postsAddRequest, Users loginUser) {
        return new Posts(
                postsAddRequest.getBody()
                , postsAddRequest.getTitle()
                , loginUser);
    }

    /* Posts add Response DTO 변환 */
    public static PostsResponse of(Long resultPostId) {
        return new PostsResponse(
                resultPostId
                , "포스트 등록 완료");
    }

    /* Posts 단일 조회 DTO 변환 */
    public static PostsDTO of(Posts posts) {
        return PostsDTO.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .body(posts.getBody())
                .userName(posts.getUsers().getUserName())
                .createdAt(posts.getRegisteredAt())
                .lastModifiedAt(posts.getUpdatedAt())
                .build();
    }

    public static PostsResponse newPostsResponse(Posts editPostsResult) {
        return new PostsResponse(
                editPostsResult.getId(),
                "포스트 수정 완료"
        );
    }

    public static PostsResponse newPostsResponse(Long postIdDelete) {
        return new PostsResponse(
                postIdDelete,
                "포스트 삭제 완료"
        );
    }

}
