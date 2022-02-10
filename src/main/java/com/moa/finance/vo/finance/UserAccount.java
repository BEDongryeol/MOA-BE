package com.moa.finance.vo.finance;

import com.moa.constant.AccountRegistrationState;
import com.moa.finance.vo.dummy.Bank;
import com.moa.finance.vo.dummy.BankTransactionHistory;
import com.moa.user.vo.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Slf4j
@ToString
@EqualsAndHashCode
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Bank bank;

    @Embedded
    private Account account;

    private String accountNickname = "";

    @Column(name = "ACCOUNT_STATE")
    @Enumerated(value = EnumType.STRING)
    private AccountRegistrationState accountRegistrationState = AccountRegistrationState.연동;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<BankTransactionHistory> histories = new ArrayList<>();

    public void addUserAccount(User user){
        List<UserAccount> userAccount = user.getUserAccount();

        if(!userAccount.contains(user)) {
            userAccount.add(this);
            log.info("이미 존재하는 계좌입니다.");
        }

        this.user = user;

        log.info("\n" + user.getName() + "님의 " + this.account.getAccountNumber() + " 계좌가 등록되었습니다.");
    }

}
