package com.moa.finance.dto.searchCondition;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccountSearchCondition {

    private String owner;
    private LocalDate birthDate;
    private String productName1;
    private String productName2;

}
