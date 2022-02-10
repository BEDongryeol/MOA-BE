package com.moa.finance.service;

import com.moa.constant.AccountRegistrationState;
import com.moa.constant.ErrorCode;
import com.moa.constant.SavingType;
import com.moa.exception.DBException;
import com.moa.finance.dto.response.AccountDetailRes;
import com.moa.finance.dto.response.LinkedAccountRes;
import com.moa.finance.dto.response.UnLinkedAccountRes;
import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.finance.Account;
import com.moa.finance.vo.finance.RegistrationManagement;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.finance.vo.finance.UserTransactionHistory;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import com.moa.util.mapper.TransactionHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final BankTransactionHistoryRepository bankTransactionHistoryRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;

    // 계좌 상세 정보 조회
    @Transactional
    public AccountDetailRes getAccountDetail(Long userId, Long userAccountId){

        UserAccount userAccount = userAccountRepository.findById(userAccountId).orElseThrow(()
            -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (userAccount.getAccountNickname().equals("")) {
            userAccount.setAccountNickname(userAccount.getAccount().getProductName());
        }

        if (userAccount.getAccountRegistrationState().equals(AccountRegistrationState.연동)) {
            Account account = userAccount.getAccount();

            return LinkedAccountRes.builder()
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

    // TODO 1-2. [적금 상품 별명 설정]
    // accountService.updateAccountNickname(user.getId(), userAccountId, accountNickname)
    public Boolean updateAccountNickname(Long userId, Long userAccountId, String accountNickname){

        // 1. userAccountRepository userAccountId로 엔티티 조회
        UserAccount userAccount = userAccountRepository.findById(userAccountId).orElseThrow(()
            -> new DBException(ErrorCode.ACCOUNT_NOT_FOUND));
        // 2. 계좌번호 뽑아내서 UserAccount에서 찾기
        String accountNumber = userAccount.getAccount().getAccountNumber();
        // 3. userAccount 닉네임 update (userGoal이랑 Cascade나 Listener 설정해서 변경 사항 적용)

        return true;

    }
}
