package com.moa.finance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDetailRes {

    protected String category;
    protected String accountNickname;
    protected String productName;
    protected String bankName;
    protected String bankImageUrl;
}
