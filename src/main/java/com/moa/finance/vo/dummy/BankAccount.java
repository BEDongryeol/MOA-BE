package com.moa.finance.vo.dummy;

import com.moa.finance.vo.finance.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "bank_account",
        uniqueConstraints = {@UniqueConstraint(
        name = "userConstraint",
        columnNames = {"owner", "birthDate", "accountNumber", "accountType"}
        )})
public class BankAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bank bank;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<BankTransactionHistory> histories = new ArrayList<>();


}