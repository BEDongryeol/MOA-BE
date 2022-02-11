package com.moa.finance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AutoTransferRes extends UpdateTransferRes{

    private Long payment;

    @Builder
    AutoTransferRes(String bankName, String productName, String savingType, Long payment) {
        super(bankName, productName, savingType);
        this.payment = payment;

    }
}
