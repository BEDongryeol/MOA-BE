package com.moa.util.mapper;

import com.moa.constant.AccountRegistrationState;
import com.moa.constant.SavingType;
import com.moa.finance.vo.dummy.BankAccount;
import com.moa.finance.vo.finance.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BankToUserAccountMapper {

    BankToUserAccountMapper INSTANCE = Mappers.getMapper(BankToUserAccountMapper.class);

    default UserAccount toUserAccount(BankAccount bankAccount){
        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(bankAccount.getAccount());
        userAccount.setBank(bankAccount.getBank());
        userAccount.setAccountRegistrationState(AccountRegistrationState.연동);
        userAccount.setAccountNickname("");
        if (bankAccount.getAccount().getProductName().contains("적금")) {
            userAccount.setSavingType(SavingType.자동이체.name());
        } else {
            userAccount.setSavingType(SavingType.해당없음.name());
        }
        return userAccount;
    }

    default List<UserAccount> toUserAccountList(List<BankAccount> bankAccounts) {
        List<UserAccount> userAccounts = new ArrayList<>();
        for (BankAccount bankAccount : bankAccounts) {
            userAccounts.add(toUserAccount(bankAccount));
        }
        return userAccounts;
    }
}
