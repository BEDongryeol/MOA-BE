package com.moa.finance.vo.dummy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "account")
public class BankTransactionHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount account;
    private BigDecimal amount;
    private BigDecimal balance;
    private String memo;
    private LocalDateTime transactionDate;
}