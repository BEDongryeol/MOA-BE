package com.moa.finance.dto.response;

import com.moa.constant.SubscriptionPeriod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class BankSavingProductsRes {
    private Long id;
    private Long bankId;
    private String bankName;
    private String bankImageUrl;
    private String productName;
    private BigDecimal highestInterest;
    private BigDecimal basicInterest;
    private String amountExplanation;
    private String subscriptionPeriod = SubscriptionPeriod.TWELVE.getValue();
    private String subscriptionLimit;
}
