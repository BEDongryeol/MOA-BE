package com.moa.util.provider;

import com.moa.constant.AccountRegistrationState;
import com.moa.constant.AccountType;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.vo.dummy.BankSavingProducts;
import com.moa.finance.vo.finance.Account;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.user.vo.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Supplier;

@Component
public class UserAccountProvider {

    Supplier<String> AccountNumberSupplier = UserAccountProvider::get;

    private static String get() {
        StringBuilder sb = new StringBuilder();
        sb.append((int)(Math.random()*(199-100+1)+100));
        sb.append("-");
        sb.append((int)(Math.random()*(1999-1000+1)+1000));
        sb.append("-");
        sb.append((int)(Math.random()*(19999-10000+1)+10000));
        return sb.toString();
    }

    public  UserAccount of(User user, BankSavingProducts product, ProductSignUpReq request) {
        // 유저 계좌에 등록
        UserAccount userAccount = new UserAccount();
        userAccount.setUser(user);
        userAccount.setSavingType(request.getSavingType());
        userAccount.setBank(product.getBank());
        userAccount.setAccount(this.createAccount(user, request));
        userAccount.setAccountRegistrationState(AccountRegistrationState.신청);
        userAccount.addUserAccount(user);
        return userAccount;
    }

    private Account createAccount(User user, ProductSignUpReq request){
        if (request.getSavingType().equals("자동이체")) {
            BigDecimal goalAmount = request.getPayment()
                                           .multiply(BigDecimal.valueOf(request.getSubscriptionPeriod()));
            return Account.builder()
                    .owner(user.getName())
                    .birthDate(user.getBirthDate())
                    .productName("장병내일준비적금")
                    .password(request.getPassword())
                    .goalAmount(goalAmount)
                    .currentAmount(BigDecimal.ZERO)
                    .accountNumber(this.AccountNumberSupplier.get())
                    .accountType(AccountType.예적금)
                    .createdDate(LocalDateTime.now())
                    .expirationDate(LocalDateTime.now().plusMonths(request.getSubscriptionPeriod()))
                    .build();
        }
        // 자유 입금
        return Account.builder()
                .owner(user.getName())
                .birthDate(user.getBirthDate())
                .productName("장병내일준비적금")
                .goalAmount(BigDecimal.ZERO)
                .password(request.getPassword())
                .currentAmount(BigDecimal.ZERO)
                .accountNumber(this.AccountNumberSupplier.get())
                .accountType(AccountType.예적금)
                .createdDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusMonths(request.getSubscriptionPeriod()))
                .build();
    }


}
