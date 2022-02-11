package com.moa.finance.repository.dummy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankTransactionHistoryRepositoryTest {
    @Autowired
    private BankTransactionHistoryRepository bankTransactionHistoryRepository;

    @DisplayName("1. 특정 계좌의 거래내력 조회")
    @Test
    void getHistoriesTest(){
        bankTransactionHistoryRepository.getHistoriesByAccountId(3L).forEach(System.out::println);
    }



}