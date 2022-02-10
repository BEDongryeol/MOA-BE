package com.moa.finance.service;

import com.moa.constant.ErrorCode;
import com.moa.exception.DBException;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.repository.dummy.BankSavingProductsRepository;
import com.moa.finance.repository.finance.RegistrationRepository;
import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.dummy.BankSavingProducts;
import com.moa.finance.vo.finance.RegistrationManagement;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import com.moa.util.mapper.ProductSignUpDtoMapper;
import com.moa.util.provider.RegistrationProvider;
import com.moa.util.provider.UserAccountProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SavingProductsServiceTest {

    @Autowired
    private BankSavingProductsRepository bankSavingProductsRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationProvider registrationProvider;
    @Autowired
    private SavingProductsService savingProductsService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserAccountProvider userAccountProvider;
    @Autowired
    private AccountService accountService;


    @DisplayName("1. RegistrationManagement 객체 생성 테스트")
    @Test
    @Transactional
    void createEntityWithParameter(){
        User user = userRepository.findById(1L).orElseThrow();
        ProductSignUpReq request = new ProductSignUpReq();
        BankSavingProducts product = bankSavingProductsRepository.findById(1L).orElseThrow();
        request.setSavingType("자동이체");
        request.setPayment(BigDecimal.valueOf(100000));
        request.setSubscriptionPeriod(8);
        request.setPassword(1234);

        StringBuilder sb = new StringBuilder();
        sb.append((int)(Math.random()*(199-100+1)+100));
        sb.append("-");
        sb.append((int)(Math.random()*(1999-1000+1)+1000));
        sb.append("-");
        sb.append((int)(Math.random()*(19999-10000+1)+10000));

        RegistrationManagement register = registrationProvider.of(user, product, request, sb.toString());
        System.out.println(registrationRepository.save(register));
    }

    @DisplayName("2. 적금 상품 계좌 가입 테스트")
    @Test
    @Transactional
    void signUpForSavingProducts(){
        User user = userRepository.findById(1L).orElseThrow();
        ProductSignUpReq request = new ProductSignUpReq();
        request.setProductId(1L);
        request.setSavingType("자동이체");
        request.setPayment(BigDecimal.valueOf(100000));
        request.setSubscriptionPeriod(8);
        request.setPassword(1234);

        System.out.println(savingProductsService.signUpForSavingProducts(user, request));
    }

    @DisplayName("3. 가입 시 생성되는 객체 조회 테스트")
    @Test
    @Transactional
    void signUpLogicTest(){

        User user = userRepository.findById(1L).orElseThrow();
        ProductSignUpReq request = new ProductSignUpReq();
        request.setProductId(1L);
        request.setSavingType("자동이체");
        request.setPayment(BigDecimal.valueOf(100000));
        request.setSubscriptionPeriod(8);
        request.setPassword(1234);

        BankSavingProducts product = bankSavingProductsRepository.findById(request.getProductId()).orElseThrow(()
                -> new DBException(ErrorCode.PRODUCT_NOT_FOUND));

        UserAccount account = userAccountRepository.save(userAccountProvider.of(user, product, request));

        RegistrationManagement register = registrationRepository.save(
                registrationProvider.of(user, product, request, account.getAccount().getAccountNumber()));

        System.out.println(account);
        System.out.println(register);

        System.out.println(accountService.getAccountDetail(1L, 4L));


    }
}