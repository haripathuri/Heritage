package com.heritage.controller;

import com.heritage.annotations.APIUrl;
import com.heritage.pojo.Account;
import com.heritage.pojo.MigrationDataSheet;
import com.heritage.service.IAccountService;
import com.heritage.util.Heritageconstants;
import io.github.millij.poi.SpreadsheetReadException;
import io.github.millij.poi.ss.reader.XlsxReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.io.IOException;
import java.util.List;

import static com.heritage.util.Heritageconstants.CURRENT;
import static com.heritage.util.Heritageconstants.SHOULD_BE_GREATERTHAN_0;

/**
 * @author Hari Pathuri
 * 5/17/2022 7:22 PM
 */

@RestController
@APIUrl
@Validated
@Slf4j
public class AccountRestController {

    private final IAccountService savingsAccountService;
    private final IAccountService currentAccountService;

    public AccountRestController(@Qualifier("savingsAccountService") IAccountService savingsAccountService,
                                 @Qualifier("currentAccountService") IAccountService currentAccountService) {
        this.savingsAccountService = savingsAccountService;
        this.currentAccountService = currentAccountService;
    }

    @GetMapping(value = "account/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAccounts(@RequestParam String accType) {
        if (accType.equalsIgnoreCase(CURRENT)) {
            return ResponseEntity.ok(currentAccountService.getAllAccounts());
        }
        return ResponseEntity.ok(savingsAccountService.getAllAccounts());
    }

    @GetMapping(value = "account/byId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getByAccountId(@RequestParam @Min(value = 1, message = SHOULD_BE_GREATERTHAN_0) Long id,
                                            @RequestParam String accType) {

        if (accType.equalsIgnoreCase(CURRENT)) {
            return ResponseEntity.ok(currentAccountService.getAccountById(id));
        }
        return ResponseEntity.ok(savingsAccountService.getAccountById(id));
    }

    @GetMapping(value = "account/byUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccountByUserId(@RequestParam @Min(value = 1, message = SHOULD_BE_GREATERTHAN_0) Long userId,
                                                @RequestParam String accType) {

        if (accType.equalsIgnoreCase(CURRENT)) {
            return ResponseEntity.ok(currentAccountService.getAccountsByUserId(userId));
        }
        return ResponseEntity.ok(savingsAccountService.getAccountsByUserId(userId));
    }

    @PostMapping(value = "account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid Account account) {
        if (CURRENT.equalsIgnoreCase(account.getAccountType())) {
            currentAccountService.createAccount(account);
        }
        savingsAccountService.createAccount(account);
        return ResponseEntity.accepted().body("Account Created");
    }

    @PutMapping(value = "account", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody @Valid Account account) {
        if (CURRENT.equalsIgnoreCase(account.getAccountType())) {
            currentAccountService.updateAccount(account);
        }
        savingsAccountService.updateAccount(account);
        return ResponseEntity.accepted().body("Account Updated");
    }

    @DeleteMapping(value = "account")
    public ResponseEntity<?> deleteUser(@RequestParam Long id)
    {
        currentAccountService.delete(id);

        savingsAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "account/migrate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> migrateData(@RequestPart("file") MultipartFile accountData) {
        final XlsxReader reader = new XlsxReader();
        log.info(" Bulk Upload accounts started ");
        try {
            List<MigrationDataSheet> migrationDataSheets = reader.read(MigrationDataSheet.class, accountData.getInputStream());
            List<String> nullColumns = Heritageconstants.getNullProperties(migrationDataSheets);
            if (nullColumns.size() > 0) {
                return ResponseEntity.badRequest().body(nullColumns);
            }

        migrationDataSheets.forEach( sheet-> {
            if (sheet.getAccountType().equalsIgnoreCase(CURRENT)) currentAccountService.bulkUpload(sheet);
            else savingsAccountService.bulkUpload(sheet);
        });

        } catch (SpreadsheetReadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }
}
