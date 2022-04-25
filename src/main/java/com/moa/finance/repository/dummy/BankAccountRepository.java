package com.moa.finance.repository.dummy;

import com.moa.finance.vo.dummy.BankTransactionHistory;
import com.moa.finance.vo.dummy.BankAccount;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long>, BankAccountRepositoryCustom {

    // User 이름과 생년월일로 데이터 연동
    // 이름, 생년월일, 계좌이름 (장병, 나라사랑)
    @Query(value = "select a from BankAccount a " +
                   "where (a.account.owner=:owner and a.account.birthDate=:birthDate) " +
                   "and (a.account.productName like %:productName1% " +
                   "or a.account.productName like %:productName2%)")
    List<BankAccount> getAccounts(@Param(value = "owner") String owner,
                                  @Param(value = "birthDate") LocalDate birthDate,
                                  @Param(value = "productName1") String productName1,
                                  @Param(value = "productName2") String productName2);

    @Query(value = "select a from BankAccount a " +
                   "where a.account.accountNumber=:accountNumber")
    BankAccount getTerminateAccount(@Param(value = "accountNumber") String accountNumber);

    // 계좌 accountId로 거래내역 데이터 조회
    @Query(value = "select a.histories from BankAccount a " +
                   "where a.id=:accountId " +
                   "order by a.id desc")
    List<BankTransactionHistory> getHistories(@Param(value = "accountId") Long accountId);
}
