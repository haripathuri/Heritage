package com.heritage.service;

import com.heritage.jpa.AccountJpa;
import com.heritage.model.AccountEntity;
import com.heritage.model.UserEntity;
import com.heritage.pojo.Account;
import com.heritage.pojo.MigrationDataSheet;
import com.heritage.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.heritage.util.Heritageconstants.CURRENT;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:29 PM
 */

@Service(value = "currentAccountService")
@Slf4j
public class CurrentAccountServiceImpl implements IAccountService {


    private final AccountJpa accountJpa;
    private final IUserService userService;

    @Autowired
    public CurrentAccountServiceImpl(AccountJpa accountJpa, IUserService userService) {
        this.accountJpa = accountJpa;
        this.userService = userService;
    }

    /**
     * @param account
     */
    @Override
    public void createAccount(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        BeanUtils.copyProperties(account, accountEntity);
        accountEntity.setUserId(new UserEntity(account.getUserId()));
        accountJpa.save(accountEntity);
    }

    /**
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        createAccount(account);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Account> getAccountById(Long id) {

        Account account = new Account();
        Optional<AccountEntity> accountEntity = Optional.ofNullable(accountJpa.findAccountEntityByAccountTypeAndId(CURRENT, id));
        if (accountEntity.isPresent()) {
            BeanUtils.copyProperties(accountEntity.get(), account);
            return Collections.singletonList(account);
        }
        return Collections.emptyList();
    }

    /**
     * @return
     */
    @Override
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        accountJpa.findAccountEntitiesByAccountType(CURRENT).forEach(accountEntity -> {
            Account account = new Account();
            BeanUtils.copyProperties(accountEntity, account);
            account.setUserId(accountEntity.getUserId().getId());
            accountList.add(account);
        });
        return accountList;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        accountJpa.deleteAccountEntityByAccountTypeAndId(CURRENT, id);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        List<Account> accountList = new ArrayList<>();
        accountJpa.findAccountEntitiesByAccountTypeAndUserId(CURRENT, new UserEntity(userId)).forEach(accountEntity -> {
            Account account = new Account();
            BeanUtils.copyProperties(accountEntity, account);
            account.setUserId(accountEntity.getUserId().getId());
            accountList.add(account);
        });
        return accountList;
    }

    /**
     * @param migrationDataSheet
     */
    @Override
    public void bulkUpload(MigrationDataSheet migrationDataSheet) {
        User user = new User();
        Account account = new Account();
        BeanUtils.copyProperties(migrationDataSheet, user);
        user.setActive('Y');
        user.setPassword("Default");
        user = userService.createUser(user);
        BeanUtils.copyProperties(migrationDataSheet, account);
        account.setUserId(user.getId());
        account.setActive('Y');
        createAccount(account);
        log.info("Bulk Upload service");

    }
}
