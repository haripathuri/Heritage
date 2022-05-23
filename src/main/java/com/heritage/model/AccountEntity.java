package com.heritage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Hari Pathuri
 * 5/17/2022 4:56 PM
 */

@Getter
@Setter
@Table(name = "tbl_account")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    public AccountEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long accountNumber;
    private String accountType;
    private Character netBankingEnabled;
    private Character debitCardStatus;
    private Double balance;
    private Double availableBalance;
    private Double overDraftBalance;
    private Character active;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    public void setAccountType(String accountType) {
        this.accountType = accountType.toUpperCase();
    }
}
