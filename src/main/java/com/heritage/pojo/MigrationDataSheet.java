package com.heritage.pojo;

import com.heritage.annotations.AccountTypeValidator;
import io.github.millij.poi.ss.model.annotations.Sheet;
import io.github.millij.poi.ss.model.annotations.SheetColumn;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Hari Pathuri
 * 5/18/2022 12:59 PM
 */

@Setter
@Getter
@Sheet(value = "Migration")
@Valid
public class MigrationDataSheet {

    // User Related Data
    @NotBlank
    @SheetColumn("FIRST NAME")
    private String firstName;
    @NotBlank
    @SheetColumn("LAST NAME")
    private String lastName;
    @NotBlank
    @SheetColumn("USER NAME")
    private String userName;
    @SheetColumn("IS ADMIN")
    private Character isAdmin;

    //Account Related Data
    @SheetColumn("ACCOUNT NUMBER")
    private Long accountNumber;
    @NotBlank
    @SheetColumn("ACCOUNT TYPE")
    @AccountTypeValidator
    private String accountType;
    @SheetColumn("NET BANKING ENABLED")
    private Character netBankingEnabled;
    @SheetColumn("DEBIT CARD ENABLED")
    private Character debitCardStatus;
    @SheetColumn("BALANCE")
    private Double balance;
    @SheetColumn("AVAILABLE_BALANCE")
    private Double availableBalance;
    @SheetColumn("OVER DRAFT BALANCE")
    private Double overDraftBalance;
}
