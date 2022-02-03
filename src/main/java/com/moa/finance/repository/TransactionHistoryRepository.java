package com.moa.finance.repository;

import com.moa.finance.vo.dummy.TransactionHistory;
import com.moa.util.mapper.TransactionHistoryMapping;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    @Query(value = "select h from TransactionHistory h " +
                   "order by h.transactionDate desc")
    List<TransactionHistory> findHistories();

    @Query(value = "select h.memo, h.amount, h.balance, h.transactionDate from TransactionHistory h " +
                   "where h.account.id=:accountId")
    List<TransactionHistoryMapping> getAccountHistories(@Param("accountId") Long accountId);
}
