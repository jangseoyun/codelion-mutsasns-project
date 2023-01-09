package com.codelion.mutsasns.domain.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmType {
    NEW_COMMENT_ON_POST
    , NEW_LIKE_ON_POST
}
