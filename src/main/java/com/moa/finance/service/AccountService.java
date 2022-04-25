package com.moa.finance.service;

import com.moa.constant.AccountRegistrationState;
import com.moa.constant.ErrorCode;
import com.moa.constant.SavingType;
import com.moa.exception.DBException;
import com.moa.finance.dto.request.UpdateTransferReq;
import com.moa.finance.dto.response.*;
import com.moa.finance.repository.dummy.BankAccountRepository;
import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.dummy.BankAccount;
import com.moa.finance.vo.finance.Account;
import com.moa.finance.vo.finance.RegistrationManagement;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.finance.vo.finance.UserTransactionHistory;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import com.moa.util.mapper.BankToUserAccountMapper;
import com.moa.util.mapper.TransactionHistoryMapper;
import com.moa.util.mapper.UserAccountDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final BankTransactionHistoryRepository bankTransactionHistoryRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    // 계좌 연동
    public List<UserAccountRes> linkMilitaryAccounts(Long userId){

        User user = userRepository.findById(userId).orElseThrow(()
                -> new DBException(ErrorCode.USER_NOT_FOUND));

        List<UserAccount> userAccounts = BankToUserAccountMapper.INSTANCE.toUserAccountList(
                bankAccountRepository.getAccounts(user.getName(), user.getBirthDate(), "나라사랑", "장병"));

        Optional<UserAccount> fromAccount = userAccounts.stream().filter(i -> i.getAccount().getProductName().contains("나라사랑")).findAny();

        if (fromAccount.isPresent()) {
            for (UserAccount userAccount : userAccounts) {
                userAccount.setFromAccount(fromAccount.get());
                userAccount.addUserAccount(user);
            }
        } else {
            log.info(user.getName() + "님의 나라사랑카드 연동에 실패하였습니다.");
        }
        return UserAccountDtoMapper.INSTANCE.toResList(userAccountRepository.findUserAccounts(user.getId()));
    }

    // 연동된 군 계좌 조회
    @Transactional
    public List<UserAccountRes> getMilitaryAccounts(Long userId){
        return UserAccountDtoMapper.INSTANCE.toResList(
                userAccountRepository.findSignedSavingProducts(userId, "장병내일준비적금"));
    }

    // 계좌 상세 정보 조회
    @Transactional
    public AccountDetailRes getAccountDetail(Long userId, Long userAccountId) {

        UserAccount userAccount = userAccountRepository.findById(userAccountId).orElseThrow(()
            -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (userAccount.getAccountNickname().equals("")) {
            userAccount.setAccountNickname(userAccount.getAccount().getProductName());
        }

        if (userAccount.getAccountRegistrationState().equals(AccountRegistrationState.연동)) {
            Account account = userAccount.getAccount();

            return LinkedAccountRes.builder()
                    .fromAccountId(userAccount.getFromAccount().getId())
                    .fromBankName(userAccount.getFromAccount().getBank().getBankName())
                    .fromProductName(userAccount.getFromAccount().getAccount().getProductName())
                    .category("군적금")
                    .productName(userAccount.getAccount().getProductName())
                    .accountNickname(userAccount.getAccountNickname())
                    .bankName(userAccount.getBank().getBankName())
                    .bankImageUrl(userAccount.getBank().getBankImageUrl())
                    .currentAmount(account.getCurrentAmount())
                    .goalAmount(account.getGoalAmount())
                    .accountNumber(account.getAccountNumber())
                    .createdDate(account.getCreatedDate())
                    .expirationDate(account.getExpirationDate())
                    .transactionHistories(
                            this.getTransactionHistory(userId)
                                    .stream().filter(i -> i.getUserAccountId().equals(userAccountId))
                                    .collect(Collectors.toList())
                    )
                    .build();

        } else {
            User user = userRepository.findById(userId).orElseThrow(()
                    -> new DBException(ErrorCode.USER_NOT_FOUND));
            // 계좌번호로 조회
            RegistrationManagement register = user.getRegistrationManagement()
                    .stream().filter(i -> Objects.equals(i.getAccountNumber(), userAccount.getAccount().getAccountNumber()))
                    .findFirst()
                    .orElseThrow(()
                            -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));
            return UnLinkedAccountRes.builder()
                    .fromAccountId(userAccount.getFromAccount().getId())
                    .category("군적금")
                    .productName(userAccount.getAccount().getProductName())
                    .accountNickname(userAccount.getAccountNickname())
                    .bankName(userAccount.getBank().getBankName())
                    .bankImageUrl(userAccount.getBank().getBankImageUrl())
                    .payment(register.getRequest().getPayment())
                    .savingType(SavingType.자동이체.name())
                    .subscriptionPeriod(register.getRequest().getSubscriptionPeriod())
                    .status(AccountRegistrationState.신청.name())
                    .build();
        }
    }

    // TODO 2. [Cache] User 로그인 후 메인화면에서 Cache 로딩
    /*
       - 연동된 계좌의 id를 반복문을 통해 미리 접근하여 데이터를 캐시에 로드
       - 보안 상의 이유로 DB에 거래 내역을 저장하지 않는다.
     */
    @Cacheable(value = "transactionHistoryCache")
    public List<UserTransactionHistory> getTransactionHistory(Long userId){
        List<UserTransactionHistory> transactionHistories = new ArrayList<>();

        userAccountRepository.findUserAccountIds(userId)
                .stream()
                .map(bankTransactionHistoryRepository::getHistoriesByAccountId)
                .map(TransactionHistoryMapper.INSTANCE::toUserList)
                .forEach(transactionHistories::addAll);
        return transactionHistories;
    }

    /* 적금 상품 별명 설정
            - 유저 계좌의 유저 정보와 접근한 유저의 정보가 같으면 실행
         */
    @Transactional
    public Boolean updateAccountNickname(Long userId, Long userAccountId, String accountNickname){

        UserAccount userAccount = isValidAccount(userId, userAccountId);
        User user = userRepository.findById(userId).orElseThrow(()
            -> new DBException(ErrorCode.USER_NOT_FOUND));

        userAccount.setAccountNickname(accountNickname);
        userAccount.addUserAccount(user);

        return true;
    }

    /* 추가 입금하기
        - 출금 계좌는 나라사랑카드로 고정
        - userAccountId로 계좌 번호를 얻고, 나라사랑카드에서 출금
    */
    @Transactional
    public ExtraSaveRes saveExtraMoney(Long userId, Long userAccountId, Long amount){
        // 1. [입금받을 계좌] userAccountRepository userAccountId로 엔티티 조회
        UserAccount targetAccount = userAccountRepository.findById(userAccountId).orElseThrow(()
                -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));
        // 2. [출금할 계좌] 상품명이 "나라사랑우대통장"인 계좌 조회
        UserAccount fromAccount = isBalanceValid(userId, amount);

        // 3. 유저 계좌의 유저 정보와 접근한 유저의 정보가 같으면 실행
        User user = userRepository.findById(userId).orElseThrow(()
                -> new DBException(ErrorCode.USER_NOT_FOUND));
        if (user.equals(targetAccount.getUser())) {
            targetAccount.getAccount().setCurrentAmount(targetAccount.getAccount().getCurrentAmount().subtract(BigDecimal.valueOf(amount)));
            fromAccount.getAccount().setCurrentAmount(fromAccount.getAccount().getCurrentAmount().add(BigDecimal.valueOf(amount)));
            targetAccount.addUserAccount(user);
            fromAccount.addUserAccount(user);
            log.info(fromAccount.getAccount().getProductName() + " 계좌에서 " + amount + "원이 " + targetAccount.getAccount().getProductName() + " 계좌로 입금되었습니다.");
        }
        return ExtraSaveRes.builder()
                .bankName(fromAccount.getBank().getBankName())
                .accountName(fromAccount.getAccount().getProductName())
                .amount(amount)
                .balance(fromAccount.getAccount().getCurrentAmount().longValue())
                .build();
    }

    /* 자동이체 변경하기
        - userAccountId 로 userAccount 조회
        - savingType & amount 변경 후 저장
     */
    @Transactional
    public UpdateTransferRes updateTransferSetting(Long userId, Long userAccountId, UpdateTransferReq request){
        // 1. [상세 계좌 조회] 유저 정보와 매칭되는 지 확인
        UserAccount userAccount = isValidAccount(userId, userAccountId);

        // 2-1. [자동이체로 변경 시] 자동이체/자유입금 -> 자동이체
        if (request.getSavingType().equals(SavingType.자동이체.name())) {
            userAccount.setSavingType(SavingType.자동이체.name());
            userAccount.getAccount().setPayment(BigDecimal.valueOf(request.getPayment()));
            return AutoTransferRes.builder()
                    .bankName(userAccount.getBank().getBankName())
                    .productName(userAccount.getAccount().getProductName())
                    .savingType(SavingType.자동이체.name())
                    .payment(userAccount.getAccount().getPayment().longValue())
                    .build();
        // 2-2. [자유입금으로 변경 시] 자동이체 -> 자유입금
        } else if (request.getSavingType().equals(SavingType.자유입금.name())) {
            UserAccount fromAccount = userAccountRepository.findById(request.getFromAccountId()).orElseThrow(()
                    -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));

            userAccount.setSavingType(SavingType.자유입금.name());
            userAccount.getAccount().setPayment(BigDecimal.ZERO);
            userAccount.setFromAccount(fromAccount);
            return FreeTransferRes.builder()
                    .bankName(fromAccount.getBank().getBankName())
                    .productName(userAccount.getAccount().getProductName())
                    .savingType(SavingType.자유입금.name())
                    .fromBankName(fromAccount.getBank().getBankName())
                    .fromAccountName(fromAccount.getAccount().getProductName())
                    .build();
        }
        return new UpdateTransferRes("12","12","12");
    }

    @Transactional
    public DeleteAccountRes terminateAccount(Long userId, Long userAccountId, Long password){
        // 해지 계좌
        UserAccount userAccount = isValidAccount(userId, userAccountId);
        // 반환 계좌
        UserAccount returnAccount = userAccount.getFromAccount();
        // 은행 계좌와 거래내역 모두 Cascade로 삭제
        BankAccount fromAccount = bankAccountRepository.getTerminateAccount(userAccount.getAccount().getAccountNumber());
        BankAccount toAccount = bankAccountRepository.getTerminateAccount(userAccount.getFromAccount().getAccount().getAccountNumber());

        if (userAccount.getAccount().getPassword().equals(password)) {
            transferAllAmount(fromAccount, toAccount);
            userAccountRepository.deleteById(userAccountId);
            bankAccountRepository.deleteById(fromAccount.getId());
        }

        return DeleteAccountRes.builder()
                .accountNumber(userAccount.getAccount().getAccountNumber())
                .bankName(userAccount.getBank().getBankName())
                .productName(userAccount.getAccount().getProductName())
                .returnBank(returnAccount.getBank().getBankName())
                .returnAccountName(returnAccount.getAccount().getProductName())
                .build();
    }

    private UserAccount isValidAccount(Long userId, Long userAccountId){
        UserAccount userAccount = userAccountRepository.findById(userAccountId).orElseThrow(()
                -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (userAccount.getUser().getId().equals(userId)) {
            return userAccount;
        }
        throw new DBException(ErrorCode.INVALID_USER);
    }

    public UserAccount isBalanceValid(Long userId, Long amount){
        UserAccount fromAccount = userAccountRepository.findSignedSavingProducts(userId, "나라사랑우대통장").get(0);
        if (fromAccount.getAccount().getCurrentAmount().compareTo(BigDecimal.valueOf(amount)) >= 0 ) {
            return fromAccount;
        }
        throw new DBException(ErrorCode.NOT_ENOUGH_BALANCE);
    }

    private void transferAllAmount(BankAccount fromBank, BankAccount toBank) {
        toBank.getAccount().setCurrentAmount(
                toBank.getAccount().getCurrentAmount()
                        .add(fromBank.getAccount().getCurrentAmount()));
        fromBank.getAccount().setCurrentAmount(BigDecimal.ZERO);
    }

}
