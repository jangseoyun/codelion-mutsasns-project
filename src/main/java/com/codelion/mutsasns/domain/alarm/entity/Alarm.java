package com.codelion.mutsasns.domain.alarm.entity;

import com.codelion.mutsasns.config.BaseEntity;
import com.codelion.mutsasns.domain.alarm.dto.AlarmType;
import com.codelion.mutsasns.domain.user.entity.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_alarm")
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Enumerated(EnumType.STRING)
    @Column(name = "alarm_type")
    private AlarmType alarmType;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "from_user_id")
    private Long fromUserId;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "text")
    private String text;

    @Builder
    public Alarm(Long id, Users users, AlarmType alarmType, LocalDateTime deletedAt, Long fromUserId, Long targetId, String text) {
        this.id = id;
        this.users = users;
        this.alarmType = alarmType;
        this.deletedAt = deletedAt;
        this.fromUserId = fromUserId;
        this.targetId = targetId;
        this.text = text;
    }
}
