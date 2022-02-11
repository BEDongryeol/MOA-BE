package com.moa.finance.service;

import com.moa.constant.SavingType;
import com.moa.finance.dto.request.UpdateTransferReq;
import com.moa.finance.dto.response.LinkedAccountRes;
import com.moa.finance.repository.dummy.BankAccountRepository;
import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.dummy.BankAccount;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.finance.vo.finance.UserTransactionHistory;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import com.moa.util.mapper.TransactionHistoryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private BankTransactionHistoryRepository bankTransactionHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @DisplayName("1. 캐시적용테스트")
    @Test
    @Transactional
    void cacheApplyingTest() {
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
    @Transactional
    void getUserTransactionHistoriesTest() throws ExecutionException, InterruptedException {
        LinkedAccountRes res = (LinkedAccountRes) accountService.getAccountDetail(1L, 3L);
        res.getTransactionHistories().forEach(System.out::println);
    }

    @DisplayName("4. 유저가 연동한 계좌 정보 조회")
    @Test
    @Transactional
    void getUserAccountsTest(){
        accountService.getMilitaryAccounts(1L).forEach(System.out::println);
    }

    @DisplayName("5. 추가 입금 테스트")
    @Test
    @Transactional
    void saveExtraMoneyTest(){
        accountService.saveExtraMoney(1L, 1L, 30000L);
        UserAccount targetAccount = userAccountRepository.findById(1L).get();
        UserAccount fromAccount = userAccountRepository.findSignedSavingProducts(1L, "나라사랑우대통장").get(0);

        System.out.println(targetAccount);
        System.out.println(fromAccount);
    }

    @DisplayName("6. 자동이체로 변경 테스트")
    @Test
    @Transactional
    void automaticTransferTest(){
        System.out.println("자동 이체로 변경시 return Data");
        System.out.println(accountService.updateTransferSetting(
                1L,
                1L,
                UpdateTransferReq.builder()
                        .payment(30000L)
                        .fromAccountId(3L)
                        .savingType(SavingType.자동이체.name())
                        .build()
        ));

        System.out.println(userAccountRepository.findById(1L).get().getFromAccount());
    }

    @DisplayName("7. 자유 입금으로 변경 테스트")
    @Test
    @Transactional
    void freeTransferTest(){
        System.out.println("자유 입금으로 변경시 return Data");
        System.out.println(accountService.updateTransferSetting(
                1L,
                1L,
                UpdateTransferReq.builder()
                        .payment(30000L)
                        .fromAccountId(3L)
                        .savingType(SavingType.자유입금.name())
                        .build()
        ));
        System.out.println(userAccountRepository.findById(1L).get().getFromAccount());
    }

    @DisplayName("8. 계좌 해지 테스트")
    @Test
    @Transactional
    void terminateAccountTest(){
        // 삭제 이전
        List<BankAccount> beforeBank = bankAccountRepository.findAll();
        List<UserAccount> beforeUser = userAccountRepository.findAll();

        System.out.println(accountService.terminateAccount(1L, 1L, 1234L));
        // 삭제 이후
        List<BankAccount> afterBank = bankAccountRepository.findAll();
        List<UserAccount> afterUser = userAccountRepository.findAll();

        assertAll(
                () -> assertNotEquals(beforeBank, afterBank),

                () -> assertNotEquals(beforeUser, afterUser)
        );
    }

    @DisplayName("9. 계좌 연동 테스트")
    @Test
    @Transactional
    void userAccountLinkTest(){
        User user = userRepository.findById(2L).orElseThrow();
        accountService.linkMilitaryAccounts(user.getId()).forEach(System.out::println);
    }
}