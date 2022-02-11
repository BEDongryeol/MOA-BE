package com.moa.finance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FreeTransferRes extends UpdateTransferRes{
    private String fromBankName;
    private String fromAccountName;

    @Builder
    public FreeTransferRes(String bankName, String productName, String savingType, String fromBankName, String fromAccountName) {
        super(bankName, productName, savingType);
        this.fromBankName = fromBankName;
        this.fromAccountName = fromAccountName;
    }
}
