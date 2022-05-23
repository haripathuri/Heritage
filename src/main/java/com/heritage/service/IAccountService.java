package com.heritage.service;

import com.heritage.pojo.Account;
import com.heritage.pojo.MigrationDataSheet;

import java.util.List;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:24 PM
 */
public interface IAccountService {

    void createAccount(Account account);
    void updateAccount(Account account);
    List<Account> getAccountById(Long Id);
    List<Account> getAllAccounts();
    void delete(Long id);

    List<Account> getAccountsByUserId(Long userId);

    void bulkUpload(MigrationDataSheet migrationDataSheet);
}
