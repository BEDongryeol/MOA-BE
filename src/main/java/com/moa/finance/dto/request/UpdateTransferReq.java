package com.moa.finance.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UpdateTransferReq {

    private Long fromAccountId;
    private String savingType;
    private Long payment;

}
