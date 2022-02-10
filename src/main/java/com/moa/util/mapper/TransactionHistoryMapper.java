package com.moa.util.mapper;

import com.moa.finance.vo.dummy.BankTransactionHistory;
import com.moa.finance.vo.finance.UserTransactionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TransactionHistoryMapper {

    TransactionHistoryMapper INSTANCE = Mappers.getMapper(TransactionHistoryMapper.class);

    default UserTransactionHistory toUser(BankTransactionHistory bank){

        UserTransactionHistory userTransactionHistory = new UserTransactionHistory();

//        userTransactionHistory.setAccount(bank.getAccount().getAccount());
        userTransactionHistory.setTransactionDate(bank.getTransactionDate());
        userTransactionHistory.setAmount(bank.getAmount());
        userTransactionHistory.setBalance(bank.getBalance());
        userTransactionHistory.setMemo(bank.getMemo());

        return userTransactionHistory;
    };

    default List<UserTransactionHistory> toUserList(List<BankTransactionHistory> banks) {
        List<UserTransactionHistory> users = new ArrayList<>();
        for (BankTransactionHistory bank : banks) {
            users.add(toUser(bank));
        }
        return users;
    }


}
