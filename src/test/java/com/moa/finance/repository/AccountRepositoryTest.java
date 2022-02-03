package com.moa.finance.repository;

import com.moa.finance.vo.dummy.TransactionHistory;
import com.moa.finance.vo.finance.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    void accountGetHistoryTest(){
        Account account = accountRepository.findById(3L).orElse(null);
        List<TransactionHistory> histories =  account.getHistories();
        System.out.println("============");
        histories.forEach(System.out::println);
        System.out.println("============");
    }

    @Test
    @Transactional
    void accountHistory(){

    }


}