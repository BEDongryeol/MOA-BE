package com.moa.finance.controller;

import com.moa.constant.ErrorCode;
import com.moa.exception.DBException;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.service.SavingProductsService;
import com.moa.finance.vo.finance.RegistrationManagement;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class SavingControllerTest {
    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SavingProductsService savingProductsService;
    private MockHttpSession session;


    @BeforeEach
    void initSession() {
        User user = userRepository.findById(1L).orElseThrow(()
                -> new DBException(ErrorCode.USER_NOT_FOUND));

        session = new MockHttpSession();
        session.setAttribute("user", user);
        System.out.println(user);
    }

    @DisplayName("1. [최고 금리 순] 유저가 가입 가능한 상품 출력")
    @Test
    @Transactional
    void getValidProductsByHighestTest() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/saving/products/high")
                        .session(session).accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
    }

    @DisplayName("2. [기본 금리 순] 유저가 가입 가능한 상품 출력")
    @Test
    @Transactional
    void getValidProductsByBasicTest() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/saving/products/basic")
                        .session(session).accept(MediaType.APPLICATION_JSON));
        result.andDo(print());
    }

    @DisplayName("3. 적금 계좌 신청")
    @Test
    @Transactional
    void signUpForSavingAccountTest() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/saving/products")
                        .param("productId", "1")
                        .param("savingType", "자동이체")
                        .param("subscriptionPeriod", "6")
                        .param("payment", "100000")
                        .param("password", "1234")
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON));
        result.andDo(print())
                .andExpect(status().isCreated());
    }

    @DisplayName("4. 계좌 상세 내역 조회")
    @Test
    @Transactional
    void getAccountDetailTest() throws Exception {
        session.clearAttributes();
        User user = userRepository.findById(2L).orElseThrow(()
                -> new DBException(ErrorCode.USER_NOT_FOUND));
        accountInit();
        session.setAttribute("user", user);

        ResultActions result = mockMvc.perform(
                get("/saving/accounts/4")
                        .session(session)
                        .accept(MediaType.APPLICATION_JSON));

        result.andDo(print())
                .andExpect(status().isOk());
    }

    private void accountInit(){
        User user = userRepository.findById(2L).orElseThrow();
        ProductSignUpReq request = new ProductSignUpReq();
        request.setProductId(1L);
        request.setSavingType("자동이체");
        request.setPayment(BigDecimal.valueOf(100000));
        request.setSubscriptionPeriod(8);
        request.setPassword(1234);

        System.out.println(savingProductsService.signUpForSavingProducts(user, request));

        System.out.println("------계좌 생성 데이터 초기화 완료------");
    }

}