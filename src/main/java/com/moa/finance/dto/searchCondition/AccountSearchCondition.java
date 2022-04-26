package com.moa.finance.dto.searchCondition;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountSearchCondition {

    private String owner;
    private LocalDate birthDate;
    private String productName1;
    private String productName2;

}
