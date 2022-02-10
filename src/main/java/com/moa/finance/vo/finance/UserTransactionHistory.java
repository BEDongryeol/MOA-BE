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

    private BigDecimal amount;
    private BigDecimal balance;
    private String memo;
    private LocalDateTime transactionDate;

    // TODO. 계좌가 연동되어 있을 때 거래 내역은 어플 실행 시 비동기로 가져오기
}
