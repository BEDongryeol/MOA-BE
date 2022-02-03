package com.moa.finance.vo.dummy;

import com.moa.finance.vo.finance.Account;
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
public class TransactionHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    private BigDecimal amount;
    private BigDecimal balance;
    private String memo;
    private LocalDateTime transactionDate;

    // TODO. 계좌가 연동되어 있을 때 거래 내역은 어플 실행 시 비동기로 가져오기
}