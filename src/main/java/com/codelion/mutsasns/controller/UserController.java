package com.codelion.mutsasns.controller;

import com.codelion.mutsasns.domain.user.dto.*;
import com.codelion.mutsasns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.userLogin(userLoginRequest);
    }
}
