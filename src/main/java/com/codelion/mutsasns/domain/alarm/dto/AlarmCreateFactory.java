package com.codelion.mutsasns.domain.alarm.dto;

import com.codelion.mutsasns.domain.alarm.entity.Alarm;

public class AlarmCreateFactory {

    public static AlarmDto from(Alarm alarm) {
        return AlarmDto.builder()
                .id(alarm.getId())
                .alarmType(alarm.getAlarmType())
                .fromUserId(alarm.getFromUserId())
                .targetId(alarm.getTargetId())
                .text(setAlarmText(alarm.getAlarmType()))
                .createdAt(alarm.getCreatedAt())
                .build();
    }

    private static String setAlarmText(AlarmType alarmType) {
        if (alarmType.equals(AlarmType.NEW_COMMENT_ON_POST)) {
            return "new comment!";
        }
        return "new like!";
    }

}
