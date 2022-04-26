package com.moa.finance.repository.dummy;

import com.moa.finance.dto.searchCondition.AccountSearchCondition;
import com.moa.finance.vo.dummy.BankAccount;
import com.moa.user.vo.User;

import java.util.List;

public interface BankAccountRepositoryCustom {

    List<BankAccount> getAccountsByQuerydsl(AccountSearchCondition condition);

    List<BankAccount> getUnlinkedAccounts(User user);
}
