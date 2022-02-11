package com.moa.finance.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class UserAccountRes {
    private Long userAccountId;
    private String bankImageUrl;
    private String accountNickname;
    private Long currentAmount;
    private Long goalAmount;
    private String category;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;
}
