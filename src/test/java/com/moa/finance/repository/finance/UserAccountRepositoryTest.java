package com.moa.finance.repository.finance;

import com.moa.finance.repository.dummy.BankAccountRepository;
import com.moa.finance.vo.dummy.BankAccount;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
class UserAccountRepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @DisplayName("1. User 이름, 생년월일로 계좌 연동 후 데이터 저장 확인")
    @Test
    @Transactional
    void userAccountLoadingTest(){
        User user = userRepository.getById(1L);
        List<BankAccount> bankAccounts = bankAccountRepository.getAccounts(user.getName(), user.getBirthDate());
        bankAccounts.forEach(System.out::println);

        System.out.println("==========================");
        if (bankAccounts.size() == 0) {
            System.out.println("불러올 계좌가 존재하지 않습니다.");
        } else {
            for (BankAccount bankAccount : bankAccounts) {
                // 복사해서 사용하는 것이 아니라 직접적으로 객체를 이어줘야한다?
                UserAccount userAccount = new UserAccount();
                userAccount.setAccount(bankAccount.getAccount());
                userAccount.setBank(bankAccount.getBank());
                userAccount.addUserAccount(user);
                userAccountRepository.save(userAccount);
                // UserAccount.account.histories 가 이미 프록시로 관리되고 있는데
                // userAccount 에 추가해버리면 이미 프록시로 관리되는게 추가되는 거니까?
            }
        }
        System.out.println("==========================");
        userAccountRepository.findUserAccounts(1L).forEach(System.out::println);
    }

    @DisplayName("2. 유저가 가입한 적금 상품의 은행 id 조회")
    @Test
    @Transactional
    void selectUserSavingProductsTest(){
        User user = userRepository.getById(1L);
        List<BankAccount> bankAccounts = bankAccountRepository.getAccounts(user.getName(), user.getBirthDate());
        bankAccounts.forEach(System.out::println);

        System.out.println("==========================");
        if (bankAccounts.size() == 0) {
            System.out.println("불러올 계좌가 존재하지 않습니다.");
        } else {
            for (BankAccount bankAccount : bankAccounts) {
                UserAccount userAccount = new UserAccount();
                userAccount.setAccount(bankAccount.getAccount());
                userAccount.setBank(bankAccount.getBank());
                userAccount.addUserAccount(user);
                userAccountRepository.save(userAccount);
            }
        }
        System.out.println("==========================");

        userAccountRepository.findSignedBankId(1L, "장병내일준비적금").forEach(System.out::println);
    }
}