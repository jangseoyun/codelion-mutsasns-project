package com.codelion.mutsasns.service;

import com.codelion.mutsasns.domain.alarm.dto.AlarmCreateFactory;
import com.codelion.mutsasns.domain.alarm.dto.AlarmDto;
import com.codelion.mutsasns.domain.alarm.dto.AlarmResponse;
import com.codelion.mutsasns.domain.user.entity.Users;
import com.codelion.mutsasns.exception.ErrorCode;
import com.codelion.mutsasns.exception.MutsaAppException;
import com.codelion.mutsasns.repository.AlarmJpaRepository;
import com.codelion.mutsasns.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final UserJpaRepository userJpaRepository;
    private final AlarmJpaRepository alarmJpaRepository;

    public AlarmResponse getPostsByUserAlarms(String loginUserName, Pageable pageable) {
        Users loginUser = userJpaRepository.findByUserName(loginUserName)
                .orElseThrow(() -> new MutsaAppException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));

        List<AlarmDto> getPostsByUserAlarms = alarmJpaRepository.findByUsersId(loginUser.getId(), pageable).stream()
                .map(alarm -> AlarmCreateFactory.from(alarm))
                .collect(Collectors.toList());

        return new AlarmResponse(getPostsByUserAlarms);
    }
}
