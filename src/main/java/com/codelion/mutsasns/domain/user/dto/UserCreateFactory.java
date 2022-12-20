package com.codelion.mutsasns.domain.user.dto;

import com.codelion.mutsasns.domain.user.entity.Users;

public class UserCreateFactory {

    /* Users Entity로 변환 */
    public static Users of(String userName, String encodePassword) {
        return new Users(
                userName,
                encodePassword
        );
    }

    /* UserJoinResponse(DTO)로 변환 */
    public static UserJoinResponse of(Users userJoinResult) {
        return new UserJoinResponse(
                userJoinResult.getId(),
                userJoinResult.getUserName()
        );
    }

}
