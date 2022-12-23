package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.Response;
import com.codelion.mutsasns.domain.user.dto.UserJoinRequest;
import com.codelion.mutsasns.domain.user.dto.UserJoinResponse;
import com.codelion.mutsasns.domain.user.dto.UserLoginRequest;
import com.codelion.mutsasns.domain.user.dto.UserLoginResponse;
import com.codelion.mutsasns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest joinRequest) {
        UserJoinResponse userJoinResult = userService.userCheckAndJoin(joinRequest);
        return Response.success(userJoinResult);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse jwt = userService.userLogin(userLoginRequest);
        return Response.success(jwt);
    }
}
