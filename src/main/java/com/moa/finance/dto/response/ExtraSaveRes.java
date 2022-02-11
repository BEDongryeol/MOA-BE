package com.moa.finance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExtraSaveRes {
    private Long amount;
    private String bankName;
    private String accountName;
    private Long balance;
}
