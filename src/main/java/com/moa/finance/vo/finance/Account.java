package com.moa.finance.vo.finance;

import com.moa.constant.AccountType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    private String owner;

    private LocalDate birthDate;

    @NonNull
    @Min(0L)
    @Max(9999L)
    private Long password;

    private String productName;

    @Builder.Default
    private BigDecimal goalAmount = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal currentAmount =  BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal payment = BigDecimal.ZERO;

    private String accountNumber;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private LocalDateTime createdDate;

    private LocalDateTime expirationDate;

}