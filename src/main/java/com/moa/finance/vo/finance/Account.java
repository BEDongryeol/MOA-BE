package com.moa.finance.vo.finance;

import com.moa.finance.vo.dummy.Bank;
import com.moa.finance.vo.dummy.TransactionHistory;
import com.moa.meta.AccountType;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "userConstraint",
        columnNames = {"owner", "birthDate", "accountNumber", "accountType"}
        )})
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO. User 객체에서 매핑
    private String owner;

    private LocalDate birthDate;

    private String productName;

    private String accountNickName = productName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    private BigDecimal goalAmount;

    private BigDecimal currentAmount;

    @Column(unique = true)
    private String accountNumber;

    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;

    private LocalDate createdDate;

    private LocalDate expirationDate;

    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> histories = new ArrayList<>();

    public Account() {}

}