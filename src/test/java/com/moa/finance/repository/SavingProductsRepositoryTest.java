package com.moa.finance.repository;

import com.moa.finance.vo.dummy.SavingProducts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class SavingProductsRepositoryTest {

    @Autowired
    private SavingProductsRepository savingProductsRepository;

    @DisplayName("적금 상품 최고 금리 순으로 조회")
    @Test
    void findInHighestOrder(){
        List<SavingProducts> res = savingProductsRepository.findProductsByHighest();
        res.forEach(System.out::println);
    }

    @DisplayName("적금 상품 기본 금리 순으로 조회")
    @Test
    void findInBasicOrder(){
        List<SavingProducts> res = savingProductsRepository.findProductsByBasic();
        res.forEach(System.out::println);
    }
}