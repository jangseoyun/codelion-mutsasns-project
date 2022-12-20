package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.user.dto.UserCreateFactory;
import com.codelion.mutsasns.domain.user.dto.UserJoinRequest;
import com.codelion.mutsasns.domain.user.dto.UserJoinResponse;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.UserException;
import com.codelion.mutsasns.repository.UserJpaRepository;
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
}
