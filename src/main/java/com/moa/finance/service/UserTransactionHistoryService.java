package com.moa.finance.service;

import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
import com.moa.finance.vo.finance.UserTransactionHistory;
import com.moa.util.mapper.TransactionHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserTransactionHistoryService {

    private final BankTransactionHistoryRepository bankTransactionHistoryRepository;

    @Async("preLoading")
    public CompletableFuture run(Long userId) {
        return new AsyncResult(getTransactionHistory(userId)).completable();
    }
    // TODO. AccountService와 중복
    @Cacheable(value = "transactionHistoryCache")
    public List<UserTransactionHistory> getTransactionHistory(Long userId){
        log.info("Async Running");
        return TransactionHistoryMapper.INSTANCE.toUserList(bankTransactionHistoryRepository.getHistoriesByAccountId(userId));
    }
}
