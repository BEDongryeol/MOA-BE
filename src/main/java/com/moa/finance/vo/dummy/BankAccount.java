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
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "useRrConstraint",
        columnNames = {"owner", "birthDate", "accountNumber", "accountType"}
        )})
public class BankAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    private Bank bank;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<BankTransactionHistory> histories = new ArrayList<>();


}