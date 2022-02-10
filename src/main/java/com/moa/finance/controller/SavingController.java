package com.moa.finance.controller;

import com.moa.exception.DBException;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.dto.response.AccountDetailRes;
import com.moa.finance.dto.response.BankSavingProductsRes;
import com.moa.finance.dto.response.ProductSignUpRes;
import com.moa.finance.service.AccountService;
import com.moa.finance.service.SavingProductsService;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/saving")
public class SavingController {

    private final SavingProductsService savingProductsService;
    private final AccountService accountService;

    /* [최고 금리순] 가입가능한 적금 상품 조회
        - 사용자가 보유한 군적금 상품은 제외
     */
    @GetMapping("/products/high")
    public ResponseEntity<List<BankSavingProductsRes>> savingProductGetByHighest(HttpSession session) throws DBException {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(savingProductsService.getValidProductsByHighest(user.getId()));
    }
    /* [기본 금리순] 가입가능한 적금 상품 조회
        - 사용자가 보유한 군적금 상품은 제외
     */
    @GetMapping("/products/basic")
    public ResponseEntity<List<BankSavingProductsRes>> savingProductGetByBasic(HttpSession session){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(savingProductsService.getValidProductsByBasic(user.getId()));
    }
    /* 적금 상품 가입
        - 자동이체, 자유입금에 따라 별개 로직 적용
     */
    @PostMapping("/products")
    public ResponseEntity<ProductSignUpRes> signUpForSavingProduct(HttpSession session, ProductSignUpReq request) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.CREATED).body(savingProductsService.signUpForSavingProducts(user, request));
    }

    // TODO. 1-1. 목표 테이블 id pathVariable -> 계좌번호로 userAccount에서 조회 -> 계좌 닉네임 설정
    /* 계좌 별명 변경
        - UserAccount Entity accountNickname 필드 수정
        !!!!!  모으기 테이블에 update 해주기 (Cascade 등으로 설정)
     */
    @PutMapping("/accounts/{userAccountId}")
    public ResponseEntity<Boolean> updateAccountNickname(HttpSession session, @PathVariable(value = "userAccountId") Long userAccountId, String accountNickname){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountService.updateAccountNickname(user.getId(), userAccountId, accountNickname));
    }

    @GetMapping("/accounts/{userAccountId}")
    public ResponseEntity<AccountDetailRes> getUserAccountDetail(HttpSession session, @PathVariable(value = "userAccountId") Long userAccountId){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountDetail(user.getId(), userAccountId));
    }

}
