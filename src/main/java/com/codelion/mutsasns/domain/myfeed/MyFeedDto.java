package com.codelion.mutsasns.domain.myfeed;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class MyFeedDto {
    private Long id;
    private String title;
    private String body;
    private String username;
    private String createAt;

    @Builder
    public MyFeedDto(Long id, String title, String body, String username, String createAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.username = username;
        this.createAt = createAt;
    }
}
