package com.moa.finance.repository.finance;

import com.moa.finance.repository.dummy.BankAccountRepository;
import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class UserTransactionHistoryRepositoryTest {

    @Autowired
    private BankTransactionHistoryRepository bankTransactionHistoryRepository;

    @Test
    @Transactional
    void getTransactionHistoriesOfAccount(){
        bankTransactionHistoryRepository.getHistoriesByAccountId(3L).forEach(System.out::println);
    }

}