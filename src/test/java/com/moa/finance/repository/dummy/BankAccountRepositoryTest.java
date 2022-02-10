package com.moa.finance.repository.dummy;

import com.moa.finance.vo.dummy.BankTransactionHistory;
import com.moa.finance.vo.finance.Account;
import com.moa.finance.vo.dummy.BankAccount;
import com.moa.constant.AccountType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @DisplayName("1. 유저의 계좌 불러오기")
    @Test
    @Transactional
    void getUsersAccount(){
        BankAccount account = bankAccountRepository.findById(1L).orElse(null);
        System.out.println(account);
    }

    @DisplayName("2. 계좌의 거래내역 불러오기")
    @Test
    void accountHistory(){
        List<BankTransactionHistory> histories = bankAccountRepository.getHistories(3L);
        histories.forEach(System.out::println);
    }

    @DisplayName("3. Unique Constraints 테스트")
    @Test
    @Transactional
    void uniqueConstraintsTest(){
        Account account = new Account();
        account.setOwner("정인우");
        account.setBirthDate(LocalDate.now());
        account.setAccountNumber("111-222");
        account.setAccountType(AccountType.입출금);

        Account account2 = new Account();
        account2.setOwner("정인우");
        account2.setBirthDate(LocalDate.now());
        account2.setAccountNumber("111-222");
        account2.setAccountType(AccountType.입출금);

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setAccount(account);

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setAccount(account2);

        assertThrows(DataIntegrityViolationException.class, () -> {
            bankAccountRepository.save(bankAccount1);
            bankAccountRepository.save(bankAccount2);
        });


    }
}