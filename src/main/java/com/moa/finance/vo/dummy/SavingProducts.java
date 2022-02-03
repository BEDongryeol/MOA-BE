package com.moa.finance.vo.dummy;

import com.moa.meta.SubscriptionPeriod;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
public class SavingProducts {
    // id, 은행, 상품명, 최고 금리, 최고 금리 기준, 기본 금리, 기본 금리 기준, 금액 설명, 기간
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankImageUrl;
    private String bankName;
    private String productName;
    private BigDecimal highestInterest;
    private BigDecimal basicInterest;
    private String amountExplanation;
    private String subscriptionPeriod = SubscriptionPeriod.TWELVE.getValue();
    private String subscriptionLimit;

}
