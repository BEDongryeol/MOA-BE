package com.moa.finance.service;

import com.moa.finance.vo.finance.UserTransactionHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserTransactionHistoryServiceTest {
    @Autowired
    private UserTransactionHistoryService userTransactionHistoryService;
    // TODO. AccountServiceTest와 중복
    @DisplayName("1. Cache 적용 테스트")
    @Test
    void cacheApplyingTest(){
        long startTime1 = System.currentTimeMillis();
        List<UserTransactionHistory> userHistory1 = userTransactionHistoryService.getTransactionHistory(3L);
        long endTime1 = System.currentTimeMillis();
        System.out.println("[캐시적용 전] 실행시간 : " + (endTime1 - startTime1));

        long startTime2 = System.currentTimeMillis();
        List<UserTransactionHistory> userHistory2 = userTransactionHistoryService.getTransactionHistory(3L);
        long endTime2 = System.currentTimeMillis();
        System.out.println("[캐시적용 후] 실행시간 : " + (endTime2 - startTime2));
    }

}