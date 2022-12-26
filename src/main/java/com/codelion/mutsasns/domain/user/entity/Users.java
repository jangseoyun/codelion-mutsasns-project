package com.codelion.mutsasns.domain.user.entity;

import com.codelion.mutsasns.domain.user.dto.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username", unique = true)
    private String userName;
    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "registered_at")
    private String registeredAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "removed_at")
    private LocalDateTime removedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.USER;

    @PrePersist
    public void onPrePersist() {
        this.registeredAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updatedAt = this.registeredAt;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
