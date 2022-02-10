package com.moa.finance.repository.dummy;

import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.dummy.BankSavingProducts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BankSavingProductsRepositoryTest {

    @Autowired
    private BankSavingProductsRepository bankSavingProductsRepository;

    @Mock
    private UserAccountRepository userAccountRepository;

    @BeforeEach
    void getBankIds(){
        when(userAccountRepository.findSignedBankId(1L, "장병내일준비적금"))
                .thenReturn(List.of(1L,2L));
    }

    @DisplayName("1. 적금 상품 최고 금리 순으로 조회")
    @Test
    void findInHighestOrder(){
        List<BankSavingProducts> res = bankSavingProductsRepository.findProductsByHighest();
        res.forEach(System.out::println);
    }

    @DisplayName("2. 적금 상품 기본 금리 순으로 조회")
    @Test
    @Transactional
    void findInBasicOrder(){
        List<BankSavingProducts> res = bankSavingProductsRepository.findProductsByBasic();
        res.forEach(System.out::println);
    }

    @DisplayName("3. 적금 상품의 은행 조회")
    @Test
    void getBankOfSavingProducts(){
        BankSavingProducts bankSavingProducts = bankSavingProductsRepository.findById(1L).orElse(null);
        System.out.println(bankSavingProducts);
    }

    @DisplayName("4. [최고 금리순] 유저가 가지고 있지 않은 적금 상품 조회")
    @Test
    @Transactional
    void getSavingProductsUserDoesNotSignedForByHighestInterestDesc(){
        List<Long> Ids = userAccountRepository.findSignedBankId(1L, "장병내일준비적금");
        bankSavingProductsRepository.findHighInterestProducts(Ids).forEach(System.out::println);
    }

    @DisplayName("5. [기본 금리순] 유저가 가지고 있지 않은 적금 상품 조회")
    @Test
    @Transactional
    void getSavingProductsUserDoesNotSignedForByBasicInterestDesc(){
        List<Long> Ids = userAccountRepository.findSignedBankId(1L, "장병내일준비적금");
        bankSavingProductsRepository.findBasicInterestProducts(Ids).forEach(System.out::println);
    }

}