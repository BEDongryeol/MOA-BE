package com.moa.finance.repository;

import com.moa.finance.vo.dummy.TransactionHistory;
import com.moa.finance.vo.finance.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
class TransactionHistoryRepositoryTest {
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void getHistoriesTest(){
        Account account = accountRepository.findById(4L).orElse(null);

        TransactionHistory th = new TransactionHistory();
        th.setAccount(account);
        th.setBalance(BigDecimal.valueOf(400000));
        th.setAmount(BigDecimal.valueOf(200000));
        th.setMemo("정기이체");
        th.setTransactionDate(LocalDateTime.now().minusDays(15).withNano(0));
        transactionHistoryRepository.save(th);
        transactionHistoryRepository.findHistories().forEach(System.out::println);
    }

    @Test
    @Transactional
    void getTransactionHistoriesOfAccount(){
        transactionHistoryRepository.getAccountHistories(3L).forEach(System.out::println);
    }
}