package com.codelion.mutsasns.domain.posts.dto;

import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor
public class PostPagingInfo {

    private String pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private Sort sort;
    private boolean first;
    private int numberOfElements;
    private boolean empty;

    @Builder
    public PostPagingInfo(String pageable, boolean last, int totalPages, int totalElements, int size, int number, Sort sort, boolean first, int numberOfElements, boolean empty) {
        this.pageable = pageable;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
        this.sort = sort;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.empty = empty;
    }
}
