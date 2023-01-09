package com.codelion.mutsasns.domain.myfeed;

import com.codelion.mutsasns.domain.posts.entity.Posts;
import org.springframework.data.domain.Page;

import java.util.List;

public class MyFeedCreateFactory {

    public static MyFeedDto from(Posts posts) {
        return MyFeedDto.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .body(posts.getBody())
                .username(posts.getUsers().getUserName())
                .createAt(posts.getCreatedAt())
                .build();
    }

    public static MyFeedListPageResponse of(List<MyFeedDto> myFeedResponseList, Page<Posts> postsPage) {
        return MyFeedListPageResponse.builder()
                .content(myFeedResponseList)
                .pageable(postsPage.getPageable())
                .last(postsPage.hasNext())
                .totalPages(postsPage.getTotalPages())
                .size(postsPage.getSize())
                .number(postsPage.getNumber())
                .sort(postsPage.getSort())
                .numberOfElements(postsPage.getNumberOfElements())
                .first(postsPage.isFirst())
                .empty(postsPage.isEmpty())
                .build();
    }

}
