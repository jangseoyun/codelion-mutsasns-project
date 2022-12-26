package com.codelion.mutsasns.domain.posts.dto;

import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

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
                .createdAt(posts.getCreatedAt())
                .lastModifiedAt(posts.getLastModifiedAt())
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

    public static PostsPageResponse newPostsPageResponse(List<PostsDTO> postsDTOList, Page<Posts> pageResult) {
        return PostsPageResponse.builder()
                .content(postsDTOList)
                .pageable("INSTANCE")
                .last(pageResult.hasNext())
                .totalPages(pageResult.getTotalPages())
                .size(pageResult.getSize())
                .number(pageResult.getNumber())
                .sort(pageResult.getSort())
                .first(pageResult.isFirst())
                .numberOfElements(pageResult.getNumberOfElements())
                .empty(pageResult.isEmpty())
                .build();
    }

}
