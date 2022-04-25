package com.moa.finance.repository.dummy;

import com.moa.finance.dto.searchCondition.AccountSearchCondition;
import com.moa.finance.vo.dummy.BankAccount;
import java.util.List;

public interface BankAccountRepositoryCustom {

    List<BankAccount> getAccountsByQuerydsl(AccountSearchCondition condition);
}
