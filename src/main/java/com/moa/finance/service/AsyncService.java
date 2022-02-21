//package com.moa.finance.service;
//
//import com.moa.finance.repository.dummy.BankTransactionHistoryRepository;
//import com.moa.finance.vo.finance.Account;
//import com.moa.finance.vo.finance.UserTransactionHistory;
//import com.moa.util.mapper.TransactionHistoryMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.ModelMap;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.Executor;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AsyncService implements Runnable {
//
//    private final Executor preLoading;
//    private AccountService accountService;
//
//    public void selectUserTransaction(Long userId, ModelMap model) throws Exception {
//        CompletableFuture<List<UserTransactionHistory>> transactionHistories = CompletableFuture.supplyAsync(
//
//                ()->{
////                    return accountService.getTransactionHistory(userId);
//
////                },
////                preLoading);
//
//        model.addAttribute("transaction", transactionHistories.get());
//
//    }
//
//    @Async("preLoading")
//    @Override
//    public void run() {
////        accountService.getTransactionHistory()
//    }
//
//
////    @Async("preLoading-async-")
////    public CompletableFuture<List<UserTransactionHistory>> getTransactions(Long userId) {
////        return new AsyncResult(getTransactionHistory(userId)).completable();
////    }
//
////    @Cacheable(value = "transactionHistoryCache")
////    public List<UserTransactionHistory> getTransactionHistory(Long userId){
////        log.info("Async Running");
////        return TransactionHistoryMapper.INSTANCE.toUserList(bankTransactionHistoryRepository.getHistoriesByAccountId(userId));
////    }
//}
