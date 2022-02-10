package com.moa.finance.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductSignUpRes {
    private String bankName;
    private String productName;
    private String accountNumber;
    private String savingType;
    private int subscriptionPeriod;
    private BigDecimal payment;
    private String status;
}
