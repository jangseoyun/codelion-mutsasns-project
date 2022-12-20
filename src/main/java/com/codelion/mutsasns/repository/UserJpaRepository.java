package com.codelion.mutsasns.repository;

import com.codelion.mutsasns.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String userName);
}
