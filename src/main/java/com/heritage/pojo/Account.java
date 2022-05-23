package com.heritage.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heritage.annotations.AccountTypeValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Hari Pathuri
 * 5/17/2022 5:08 PM
 */

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    private Long id;
    @Min(1)
    private Long accountNumber;
    @NotBlank
    @AccountTypeValidator
    private String accountType;
    private Character netBankingEnabled;
    private Character debitCardStatus;
    private Double balance;
    private Double availableBalance;
    private Double overDraftBalance;
    private Character active;
    private User user;
    @Min(1)
    private Long userId;

    public void setNetBankingEnabled(Character netBankingEnabled) {
        if (netBankingEnabled != null) this.netBankingEnabled = netBankingEnabled;
        else this.netBankingEnabled = 'N';
    }

    public void setDebitCardStatus(Character debitCardStatus) {
        if (debitCardStatus != null) this.debitCardStatus = debitCardStatus;
        else this.debitCardStatus = 'N';
    }
}
