package com.moa.finance.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UpdateTransferRes {
    private String bankName;
    private String productName;
    private String savingType;
}
