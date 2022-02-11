package com.moa.finance.dto.request;

import com.moa.constant.SavingType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@ToString
public class ProductSignUpReq {
    @NonNull
    private Long productId;
    // 적금 방식
    private String savingType = SavingType.자동이체.name();
    // 기간
    private int subscriptionPeriod; // 기간 6개월~전역일자까지
    // 월 납입액
    @Min(value = 10000)
    @Max(value = 200000)
    private BigDecimal payment;
    // 비밀번호
    @Min(value = 0L)
    @Max(value = 9999L)
    private Long password;

    private String accountNickname = "";

}
