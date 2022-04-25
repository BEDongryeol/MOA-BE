package com.moa.finance.vo.finance;

import com.moa.constant.AccountRegistrationState;
import com.moa.constant.SavingType;
import com.moa.finance.vo.dummy.Bank;
import com.moa.user.vo.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Slf4j
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WITHDRAWAL_ACCOUNT_ID")
    @ToString.Exclude
    private UserAccount fromAccount;

    private String accountNickname = "";

    private String savingType = SavingType.자동이체.name();

    @Column(name = "ACCOUNT_STATE")
    @Enumerated(value = EnumType.STRING)
    private AccountRegistrationState accountRegistrationState = AccountRegistrationState.연동;

    public void addUserAccount(User user){
        List<UserAccount> userAccount = user.getUserAccount();

        if(!userAccount.contains(user)) {
            userAccount.add(this);
            log.info("이미 존재하는 계좌입니다.");
        }

        this.user = user;

        log.info("\n" + user.getName() + "님의 " + this.account.getAccountNumber() + " 계좌가 등록되었습니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserAccount that = (UserAccount) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(account.getAccountNumber());
    }
}
