package com.moa.finance.vo.finance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserTransactionHistory {
    private Long id;
    private Long userAccountId;
    private BigDecimal amount;
    private BigDecimal balance;
    private String memo;
    private LocalDateTime transactionDate;

}
