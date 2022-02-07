package com.moa.finance.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class UserSavingProductsRes {
    private String productName;
    private LocalDate birthDate;
    private LocalDate createdDate;
    private LocalDate expirationDate;
    private BigDecimal goalAmount;
}
