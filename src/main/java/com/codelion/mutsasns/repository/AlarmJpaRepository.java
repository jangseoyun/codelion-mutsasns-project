package com.codelion.mutsasns.repository;

import com.codelion.mutsasns.domain.alarm.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmJpaRepository extends JpaRepository<Alarm, Long> {
    Page<Alarm> findByUsersId(Long userId, Pageable pageable);
}
