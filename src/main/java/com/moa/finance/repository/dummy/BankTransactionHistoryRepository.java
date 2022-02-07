package com.moa.finance.repository.dummy;

import com.moa.finance.vo.dummy.BankTransactionHistory;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransactionHistoryRepository extends JpaRepository<BankTransactionHistory, Long> {
    // 특정 계좌의 거래내역 가져오기
    @Query(value = "select a.histories from BankAccount a " +
                   "where a.id=:accountId " +
                   "order by a.id desc")
    List<BankTransactionHistory> getHistoriesByAccountId(@Param(value = "accountId") Long accountId);

}
