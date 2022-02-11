package com.moa.finance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class UnLinkedAccountRes extends AccountDetailRes {

    private Long payment;
    private String savingType;
    private int subscriptionPeriod;
    private String status;

    @Builder
    public UnLinkedAccountRes(Long fromAccountId, String category, String accountNickname, String productName, String bankName, String bankImageUrl,  BigDecimal payment, String savingType, int subscriptionPeriod, String status) {
        super(fromAccountId, category, accountNickname, productName, bankName, bankImageUrl);
        this.payment = payment.longValue();
        this.savingType = savingType;
        this.subscriptionPeriod = subscriptionPeriod;
        this.status = status;
    }
}
