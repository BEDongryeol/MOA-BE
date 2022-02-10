package com.moa.finance.service;

import com.moa.constant.AccountRegistrationState;
import com.moa.constant.ErrorCode;
import com.moa.constant.SavingType;
import com.moa.exception.DBException;
import com.moa.finance.dto.response.LinkedAccountRes;
import com.moa.finance.dto.response.UnLinkedAccountRes;
import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.finance.vo.finance.UserTransactionHistory;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import com.moa.util.mapper.TransactionHistoryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private BankTransactionHistoryRepository bankTransactionHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("1. 캐시적용테스트")
    @Test
    void cacheApplyingTest(){
        long startTime1 = System.currentTimeMillis();
        List<UserTransactionHistory> userHistory1 = accountService.getTransactionHistory(1L);
        long endTime1 = System.currentTimeMillis();
        System.out.println("[캐시적용 전] 실행시간 : " + (endTime1 - startTime1));

        long startTime2 = System.currentTimeMillis();
        List<UserTransactionHistory> userHistory2 = accountService.getTransactionHistory(1L);
        long endTime2 = System.currentTimeMillis();
        System.out.println("[캐시적용 후] 실행시간 : " + (endTime2 - startTime2));

        System.out.println("===============");
        userHistory2.forEach(System.out::println);
        System.out.println("===============");
        assertEquals(userHistory1, userHistory2);

    }

    @DisplayName("2. stream 테스트")
    @Test
    @Transactional
    void streamTest(){
        List<UserTransactionHistory> list = new ArrayList<>();
        userAccountRepository.findUserAccountIds(1L) //1, 2, 3 //유저의 UseraccountId 가져오기
                .stream()
                .map(bankTransactionHistoryRepository::getHistoriesByAccountId)
                .map(TransactionHistoryMapper.INSTANCE::toUserList)
                .forEach(list::addAll);

        list.forEach(System.out::println);
    }

    @DisplayName("3. UserId, UserAccountId로 거래내역 조회")
    @Test
    void getUserTransactionHistoriesTest(){
        LinkedAccountRes res = (LinkedAccountRes) accountService.getAccountDetail(1L, 3L);
        res.getTransactionHistories().forEach(System.out::println);
    }

}