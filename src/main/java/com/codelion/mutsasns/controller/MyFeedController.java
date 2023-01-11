package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.myfeed.MyFeedListPageResponse;
import com.codelion.mutsasns.service.MyFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class MyFeedController {
            private final MyFeedService myFeedService;

            /**
             * 해당 유저의 작성 리스트(마이피드)
             * @param authentication(인증), 페이징
             * @return 가공한 myFeedDto, 페이징 정보
             */
            @GetMapping("/my")
            public Response<MyFeedListPageResponse> getMyFeedList(Authentication authentication
                    , @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
                String loginUserName = authentication.getName();
                MyFeedListPageResponse myFeedListAll = myFeedService.getMyFeedListAll(loginUserName, pageable);
                return Response.success(myFeedListAll);
    }
}
