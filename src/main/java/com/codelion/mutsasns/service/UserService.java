package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.user.dto.*;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.UserException;
import com.codelion.mutsasns.repository.UserJpaRepository;
import com.codelion.mutsasns.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60;

    public UserJoinResponse userCheckAndJoin(UserJoinRequest userJoinDTO) {
        //회원 중복 체크
        userJpaRepository.findByUserName(userJoinDTO.getUserName())
                .ifPresent(user -> {
                    throw new UserException(ErrorCode.DUPLICATED_USER_NAME, String.format("username: %s", userJoinDTO.getUserName()));
                });

        //회원가입
        String passwordEncode = encoder.encode(userJoinDTO.getPassword());
        Users userJoinResult = userJpaRepository.save(UserCreateFactory.of(userJoinDTO.getUserName(), passwordEncode));

        return UserCreateFactory.of(userJoinResult);
    }

    public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
        Users loginUser = userJpaRepository.findByUserName(userLoginRequest.getUserName())
                .orElseThrow(() -> new UserException(ErrorCode.USERNAME_NOT_FOUND, String.format("%s", "가입된 유저가 아닙니다")));

        if (!encoder.matches(userLoginRequest.getPassword(), loginUser.getPassword())) {
            throw new UserException(ErrorCode.INVALID_PASSWORD, String.format("%s", "아이디 또는 비밀번호가 일치하지 않습니다"));
        }
        String token = JwtUtil.createToken(userLoginRequest.getUserName(), secretKey, expireTimeMs);
        return new UserLoginResponse(token);
    }

    public Users getUserByUserName(String userName) {
        return userJpaRepository.findByUserName(userName)
                .orElseThrow(() -> new UserException(ErrorCode.USERNAME_NOT_FOUND, ""));
    }
}
