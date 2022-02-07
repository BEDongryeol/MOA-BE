package com.moa.finance.vo.finance;

import com.moa.meta.AccountType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Account {
    private String owner;

    private LocalDate birthDate;

    private String productName;

    private BigDecimal goalAmount = BigDecimal.ZERO;

    private BigDecimal currentAmount =  BigDecimal.ZERO;

    private String accountNumber;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private LocalDate createdDate;

    private LocalDate expirationDate;

<<<<<<< HEAD
    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> histories = new ArrayList<>();

    public Account() {}

=======
>>>>>>> develop-inwoo
}