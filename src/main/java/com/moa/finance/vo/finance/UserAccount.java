package com.moa.finance.vo.finance;

import com.moa.finance.vo.dummy.Bank;
import com.moa.user.vo.User;
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
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Bank bank;

    @Embedded
    private Account account;

    // TODO. '다음에 하기' 를 누르면 상품명으로 초기화하기
    private String accountNickname;
    // TODO. 캐시 정책을 통해서 DB에서 보관 X
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserTransactionHistory> histories = new ArrayList<>();

    public void addUserAccount(User user){
        user.getUserAccount().add(this);
        this.user = user;
        log.info("\n" + user.getName() + "님의 " + this.account.getAccountNumber() + " 계좌가 등록돼었습니다.");
    }

}
