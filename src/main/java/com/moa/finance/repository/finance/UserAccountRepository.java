package com.moa.finance.repository.finance;

import com.moa.finance.vo.finance.UserAccount;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    // User가 연동한 계좌 데이터 조회
    @Query(value = "select u from UserAccount u " +
                   "where u.user.id = :userId")
    List<UserAccount> findUserAccounts(@Param("userId") Long userId);

    @Query(value = "select u.bank.id from UserAccount u " +
                   "where u.account.productName = :productName " +
                   "and u.user.id = :userId")
    List<Long> findSignedBankId(@Param("userId") Long userId,
                                @Param("productName") String productName);
}
