package com.moa.util.provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAccountProviderTest {

    @Autowired
    private UserAccountProvider userAccountProvider;

    @DisplayName("1. 계좌번호 무작위 생성 테스트")
    @Test
    void createAccountNumberTest(){
        System.out.println(userAccountProvider.AccountNumberSupplier.get());
        System.out.println(userAccountProvider.AccountNumberSupplier.get());
        System.out.println(userAccountProvider.AccountNumberSupplier.get());
    }
}