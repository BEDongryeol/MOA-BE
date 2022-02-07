package com.moa.finance.dto;

import com.moa.meta.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserAccountRes {
    private Long id;
    private String accountName;
    private String accountNumber;
    private String bankName;
    private BigDecimal currentAmount;
    private String owner;
    private AccountType accountType;
}
