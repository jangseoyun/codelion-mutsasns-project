package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.myfeed.MyFeedCreateFactory;
import com.codelion.mutsasns.domain.myfeed.MyFeedDto;
import com.codelion.mutsasns.domain.myfeed.MyFeedListPageResponse;
import com.codelion.mutsasns.domain.posts.entity.Posts;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.MutsaAppException;
import com.codelion.mutsasns.repository.PostsJpaRepository;
import com.codelion.mutsasns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyFeedService {

    private final UserJpaRepository userJpaRepository;
    private final PostsJpaRepository postsJpaRepository;

    public MyFeedListPageResponse getMyFeedListAll(String loginUserName, Pageable pageable) {
        Users getUser = userJpaRepository.findByUserName(loginUserName)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.USERNAME_NOT_FOUND, "해당 user가 존재하지 않습니다"));

        Page<Posts> getMyFeedPaging = postsJpaRepository.findByUsersId(getUser.getId(), pageable);
        List<MyFeedDto> myFeedDtoList = getMyFeedPaging.stream()
                .map(posts -> MyFeedCreateFactory.from(posts))
                .collect(Collectors.toList());
        return MyFeedCreateFactory.of(myFeedDtoList, getMyFeedPaging);
    }
}
