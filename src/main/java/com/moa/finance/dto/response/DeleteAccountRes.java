package com.moa.finance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class DeleteAccountRes {
    private String bankName;
    private String productName;
    private String accountNumber;

    private String returnBank;
    private String returnAccountName;

}
