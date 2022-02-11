package com.moa.finance.service;

import com.moa.constant.ErrorCode;
import com.moa.exception.DBException;
import com.moa.finance.dto.request.ProductSignUpReq;
import com.moa.finance.dto.response.BankSavingProductsRes;
import com.moa.finance.dto.response.ProductSignUpRes;
import com.moa.finance.repository.dummy.BankSavingProductsRepository;
import com.moa.finance.repository.finance.RegistrationRepository;
import com.moa.finance.repository.finance.UserAccountRepository;
import com.moa.finance.vo.dummy.BankSavingProducts;
import com.moa.finance.vo.finance.UserAccount;
import com.moa.user.vo.User;
import com.moa.util.mapper.ProductSignUpDtoMapper;
import com.moa.util.mapper.SavingProductsMapper;
import com.moa.util.provider.RegistrationProvider;
import com.moa.util.provider.UserAccountProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingProductsService {

    private final BankSavingProductsRepository bankSavingProductsRepository;
    private final UserAccountRepository userAccountRepository;
    private final RegistrationRepository registrationRepository;
    private final RegistrationProvider registrationProvider;
    private final UserAccountProvider userAccountProvider;

    // [연동된 군적금 조회]
    public List<UserAccount> getSavingsOfUser(Long userId){
//        MultiValueMap<String, >
        return userAccountRepository.findSignedSavingProducts(userId, "장병내일준비적금");
    }


    // [가입 가능한 적금 상품 조회] 최고 금리순
    @Transactional
    public List<BankSavingProductsRes> getValidProductsByHighest(Long userId){
        return SavingProductsMapper.INSTANCE.toDtoList(
                bankSavingProductsRepository.findHighInterestProducts(
                        userAccountRepository.findSignedBankId(userId, "장병내일준비적금")
        ));
    }

    // [가입 가능한 적금 상품 조회] 기본 금리 순
    @Transactional
    public List<BankSavingProductsRes> getValidProductsByBasic(Long userId){
        return SavingProductsMapper.INSTANCE.toDtoList(
                bankSavingProductsRepository.findBasicInterestProducts(
                        userAccountRepository.findSignedBankId(userId, "장병내일준비적금")
        ));
    }

    // [적금 상품 가입]
    @Transactional
    public ProductSignUpRes signUpForSavingProducts(User user, ProductSignUpReq request){
        // 1. 적금 상품을 조회한다.
        BankSavingProducts product = bankSavingProductsRepository.findById(request.getProductId()).orElseThrow(()
                -> new DBException(ErrorCode.PRODUCT_NOT_FOUND));
        /* 2. UserAccount 를 생성하여 저장
            - UserAccount 테이블에 추가
            - User userAccount 에 추가
            - accountNumber 무작위로 생성
         */

        String accountNumber = userAccountRepository.save(userAccountProvider.of(user, product, request))
                .getAccount().getAccountNumber();

        /* 3. Registration 를 생성하고 저장
            -
        */
        return ProductSignUpDtoMapper.INSTANCE.toRes(
                registrationRepository.save(
                        registrationProvider.of(user, product, request, accountNumber)));
    }

}
