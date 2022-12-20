package com.codelion.mutsasns.domain.user.entity;

import com.codelion.mutsasns.domain.user.dto.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "registered_at")
    private LocalDateTime registeredAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "role")
    private UserRole role = UserRole.USER;

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
