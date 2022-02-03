package com.moa.finance.vo.dummy;

import com.moa.finance.vo.finance.Account;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Bank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;

    @OneToMany(mappedBy = "bank")
    private List<Account> accounts = new ArrayList<>();
}
