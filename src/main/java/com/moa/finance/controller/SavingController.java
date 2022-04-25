package com.moa.finance.controller;

import com.moa.exception.DBException;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.dto.request.UpdateTransferReq;
import com.moa.finance.dto.response.*;
import com.moa.finance.service.AccountService;
import com.moa.finance.service.SavingProductsService;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SavingController {

    private final SavingProductsService savingProductsService;
    private final AccountService accountService;
    private final UserRepository userRepository;

    /* [군 계좌 연동] API 대체
        - userId로 정보 불러온 후 연동
        - 이름, 생년월일, 군 관련 용어를 포함한 상품명 조회
     */
    @GetMapping("/saving/link")
    public ResponseEntity<List<UserAccountRes>> linkAccounts(@RequestHeader("X-USER-ID") @NotNull Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.linkMilitaryAccounts(userId));
    }

    /* [최고 금리순] 가입가능한 적금 상품 조회
        - 사용자가 보유한 군적금 상품은 제외
     */
    @GetMapping("/saving/products/high")
    public ResponseEntity<List<BankSavingProductsRes>> savingProductGetByHighest(@RequestHeader("X-USER-ID") @NotNull Long userId) throws DBException {
        return ResponseEntity.status(HttpStatus.OK).body(savingProductsService.getValidProductsByHighest(userId));
    }

    /* [기본 금리순] 가입가능한 적금 상품 조회
        - 사용자가 보유한 군적금 상품은 제외
     */
    @GetMapping("/saving/products/basic")
    public ResponseEntity<List<BankSavingProductsRes>> savingProductGetByBasic(@RequestHeader("X-USER-ID") @NotNull Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(savingProductsService.getValidProductsByBasic(userId));
    }

    /* 적금 상품 가입
        - 자동이체, 자유입금에 따라 별개 로직 적용
     */
    @PostMapping("/saving/products")
    public ResponseEntity<ProductSignUpRes> signUpForSavingProduct(@RequestHeader("X-USER-ID") @NotNull Long userId, ProductSignUpReq request) {
        User user = userRepository.findById(userId).orElseThrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(savingProductsService.signUpForSavingProducts(user, request));
    }

    /* 군 적금 상품 조회
        - 연동된 상품이 상위로 올라오도록 조회
     */
    @GetMapping("/saving/accounts")
    public ResponseEntity<List<UserAccountRes>> getUserAccounts(@RequestHeader("X-USER-ID") @NotNull Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMilitaryAccounts(userId));
    }

    /* 계좌 상세 정보 보기
        - 연동된 계좌 : 계좌 관련 정보 및 거래 내역
        - 신청 중인 계좌 : 신청 정보
    */
    @GetMapping("/saving/accounts/{userAccountId}")
    public ResponseEntity<AccountDetailRes> getUserAccountDetail(@RequestHeader("X-USER-ID") @NotNull Long userId, @PathVariable(value = "userAccountId") Long userAccountId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountDetail(userId, userAccountId));
    }

    /* 추가 입금하기
        - 출금 계좌는 나라사랑카드로 고정
        - userAccountId로 계좌 번호를 얻고, 나라사랑카드에서 출금
     */
    @PostMapping("/saving/accounts/{userAccountId}/save")
    public ResponseEntity<ExtraSaveRes> saveExtraMoney(@RequestHeader("X-USER-ID") @NotNull Long userId, @PathVariable("userAccountId") Long userAccountId, Long amount){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.saveExtraMoney(userId, userAccountId, amount));
    }

    /* 계좌 별명 변경
        - UserAccount Entity accountNickname 필드 수정
        !!!!!  모으기 테이블에 update 해주기 (Cascade 등으로 설정)
     */
    @PutMapping("/saving/accounts/{userAccountId}/accountNickname")
    public ResponseEntity<Boolean> updateAccountNickname(@RequestHeader("X-USER-ID") @NotNull Long userId, @PathVariable(value = "userAccountId") Long userAccountId, String accountNickname){
        return ResponseEntity.created(URI.create(String.format("/saving/accounts/%s", userAccountId))).body(accountService.updateAccountNickname(userId, userAccountId, accountNickname));
    }

    /* 자동이체 변경
        - 자동이체, 자유입금 변경
        - 납입액 변경
     */
    @PutMapping("/saving/accounts/{userAccountId}/savingType")
    public ResponseEntity<UpdateTransferRes> updateAccount(@RequestHeader("X-USER-ID") @NotNull Long userId, @PathVariable(value = "userAccountId") Long userAccountId, UpdateTransferReq request) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateTransferSetting(userId, userAccountId, request));
    }

    /* 중도 해지
        - UserAccount, BankAccount 에서 삭제
        - BankTransaction 데이터
            - cascade = CascadeType.ALL, orphanRemoval = true
     */
    @DeleteMapping("/saving/accounts/{userAccountId}")
    public ResponseEntity<DeleteAccountRes> deleteMap(@RequestHeader("X-USER-ID") @NotNull Long userId, @PathVariable(value = "userAccountId") Long userAccountId, Long password){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.terminateAccount(userId, userAccountId, password));
    }
}
