package com.heritage.jpa;

import com.heritage.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:12 PM
 */
public interface UserJpa extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityById(Long id);
}
