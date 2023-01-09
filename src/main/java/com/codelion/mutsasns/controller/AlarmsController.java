package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.alarm.dto.AlarmResponse;
import com.codelion.mutsasns.service.AlarmService;
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
public class AlarmsController {

    private final AlarmService alarmService;

    @GetMapping("/alarms")
    public Response<AlarmResponse> getPostsByUserAlarms(Authentication authentication
            , @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable) {
        String loginUserName = authentication.getName();
        AlarmResponse postsByUserAlarms = alarmService.getPostsByUserAlarms(loginUserName, pageable);
        return Response.success(postsByUserAlarms);
    }
}
