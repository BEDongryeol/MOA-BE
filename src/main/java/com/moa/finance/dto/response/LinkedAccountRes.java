package com.moa.finance.dto.response;

import com.moa.finance.vo.finance.UserTransactionHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class LinkedAccountRes extends AccountDetailRes {
    private Long currentAmount;
    private Long goalAmount;
    private String accountNumber;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;
    private List<UserTransactionHistory> transactionHistories;

    @Builder
    public LinkedAccountRes(String category, String accountNickname, String bankName, String bankImageUrl, String productName, BigDecimal currentAmount, BigDecimal goalAmount, String accountNumber, LocalDateTime createdDate, LocalDateTime expirationDate, List<UserTransactionHistory> transactionHistories) {
        super(category, accountNickname, productName, bankName, bankImageUrl);
        this.currentAmount = currentAmount.longValue();
        this.goalAmount = goalAmount.longValue();
        this.accountNumber = accountNumber;
        this.createdDate = createdDate;
        this.expirationDate = expirationDate;
        this.transactionHistories = transactionHistories;
    }
}
