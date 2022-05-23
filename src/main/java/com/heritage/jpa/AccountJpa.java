package com.heritage.jpa;

import com.heritage.model.AccountEntity;
import com.heritage.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:15 PM
 */
public interface AccountJpa extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findAccountEntitiesByAccountType(String accType);

    List<AccountEntity> findAccountEntitiesByUserId(UserEntity user);
    
    AccountEntity findAccountEntityByAccountTypeAndId(String type, Long id);

    List<AccountEntity> findAccountEntitiesByAccountTypeAndUserId(String type, UserEntity user);
    
    void deleteAccountEntityByAccountTypeAndId(String type, Long id);
}
