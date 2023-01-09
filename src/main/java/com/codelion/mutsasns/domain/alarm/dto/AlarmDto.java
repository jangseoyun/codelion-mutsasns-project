package com.codelion.mutsasns.domain.alarm.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmDto {

    private Long id;
    private AlarmType alarmType;
    private Long fromUserId;
    private Long targetId;
    private String text;
    private String createdAt;

    @Builder
    public AlarmDto(Long id, AlarmType alarmType, Long fromUserId, Long targetId, String text, String createdAt) {
        this.id = id;
        this.alarmType = alarmType;
        this.fromUserId = fromUserId;
        this.targetId = targetId;
        this.text = text;
        this.createdAt = createdAt;
    }
}
