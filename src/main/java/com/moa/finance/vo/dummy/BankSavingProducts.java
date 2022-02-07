package com.moa.finance.vo.dummy;

import com.moa.meta.SubscriptionPeriod;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString
public class BankSavingProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
    private String productName;
    private BigDecimal highestInterest;
    private BigDecimal basicInterest;
    private String amountExplanation;
    private String subscriptionPeriod = SubscriptionPeriod.TWELVE.getValue();
    private String subscriptionLimit;

}
