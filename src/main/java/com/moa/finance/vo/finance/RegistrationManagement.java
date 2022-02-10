package com.moa.finance.vo.finance;

import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.vo.dummy.BankSavingProducts;
import com.moa.user.vo.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationManagement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @ToString.Exclude
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private BankSavingProducts bankSavingProducts;

    private String accountNumber;

    @Embedded
    private ProductSignUpReq request;

    private String status;

    public void addRegister(User user){
        List<RegistrationManagement> registers = user.getRegistrationManagement();
        if (!registers.contains(this)){
            registers.add(this);
        }
        this.user = user;
        log.info("\n" + user.getName() + "님의 " + this.bankSavingProducts.getProductName() + " 계좌 개설이 신청되었습니다..");
    }

}
