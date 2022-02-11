package com.moa.finance.controller;

import com.moa.exception.DBException;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.dto.request.UpdateTransferReq;
import com.moa.finance.dto.response.*;
import com.moa.finance.service.AccountService;
import com.moa.finance.service.SavingProductsService;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SavingController {

    private final SavingProductsService savingProductsService;
    private final AccountService accountService;

    /* [군 계좌 연동] API 대체
        - userId로 정보 불러온 후 연동
        - 이름, 생년월일, 군 관련 용어를 포함한 상품명 조회
     */
    @GetMapping("/saving/link")
    public ResponseEntity<List<UserAccountRes>> linkAccounts(HttpSession session){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.linkMilitaryAccounts(user.getId()));
    }

    /* [최고 금리순] 가입가능한 적금 상품 조회
        - 사용자가 보유한 군적금 상품은 제외
     */
    @GetMapping("/saving/products/high")
    public ResponseEntity<List<BankSavingProductsRes>> savingProductGetByHighest(HttpSession session) throws DBException {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(savingProductsService.getValidProductsByHighest(user.getId()));
    }

    /* [기본 금리순] 가입가능한 적금 상품 조회
        - 사용자가 보유한 군적금 상품은 제외
     */
    @GetMapping("/saving/products/basic")
    public ResponseEntity<List<BankSavingProductsRes>> savingProductGetByBasic(HttpSession session){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(savingProductsService.getValidProductsByBasic(user.getId()));
    }

    /* 적금 상품 가입
        - 자동이체, 자유입금에 따라 별개 로직 적용
     */
    @PostMapping("/saving/products")
    public ResponseEntity<ProductSignUpRes> signUpForSavingProduct(HttpSession session, ProductSignUpReq request) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.CREATED).body(savingProductsService.signUpForSavingProducts(user, request));
    }

    /* 군 적금 상품 조회
        - 연동된 상품이 상위로 올라오도록 조회
     */
    @GetMapping("/saving/accounts")
    public ResponseEntity<List<UserAccountRes>> getUserAccounts(HttpSession session){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMilitaryAccounts(user.getId()));
    }

    /* 계좌 상세 정보 보기
        - 연동된 계좌 : 계좌 관련 정보 및 거래 내역
        - 신청 중인 계좌 : 신청 정보
    */
    @GetMapping("/saving/accounts/{userAccountId}")
    public ResponseEntity<AccountDetailRes> getUserAccountDetail(HttpSession session, @PathVariable(value = "userAccountId") Long userAccountId) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountDetail(user.getId(), userAccountId));
    }

    /* 추가 입금하기
        - 출금 계좌는 나라사랑카드로 고정
        - userAccountId로 계좌 번호를 얻고, 나라사랑카드에서 출금
     */
    @PostMapping("/saving/accounts/{userAccountId}/save")
    public ResponseEntity<ExtraSaveRes> saveExtraMoney(HttpSession session, @PathVariable("userAccountId") Long userAccountId, Long amount){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.saveExtraMoney(user.getId(), userAccountId, amount));
    }

    /* 계좌 별명 변경
        - UserAccount Entity accountNickname 필드 수정
        !!!!!  모으기 테이블에 update 해주기 (Cascade 등으로 설정)
     */
    @PutMapping("/saving/accounts/{userAccountId}/accountNickname")
    public ResponseEntity<Boolean> updateAccountNickname(HttpSession session, @PathVariable(value = "userAccountId") Long userAccountId, String accountNickname){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.created(URI.create(String.format("/saving/accounts/%s", userAccountId))).body(accountService.updateAccountNickname(user.getId(), userAccountId, accountNickname));
    }

    /* 자동이체 변경
        - 자동이체, 자유입금 변경
        - 납입액 변경
     */
    @PutMapping("/saving/accounts/{userAccountId}/savingType")
    public ResponseEntity<UpdateTransferRes> updateAccount(HttpSession session, @PathVariable(value = "userAccountId") Long userAccountId, UpdateTransferReq request) {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateTransferSetting(user.getId(), userAccountId, request));
    }

    /* 중도 해지
        - UserAccount, BankAccount 에서 삭제
        - BankTransaction 데이터
            - cascade = CascadeType.ALL, orphanRemoval = true
     */
    @DeleteMapping("/saving/accounts/{userAccountId}")
    public ResponseEntity<DeleteAccountRes> deleteMap(HttpSession session, @PathVariable(value = "userAccountId") Long userAccountId, Long password){
        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(accountService.terminateAccount(user.getId(), userAccountId, password));
    }
}
