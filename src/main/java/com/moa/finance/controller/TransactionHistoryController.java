package com.moa.finance.controller;

import com.moa.finance.service.AsyncService;
import com.moa.finance.vo.finance.UserTransactionHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class TransactionHistoryController {

    private final AsyncService asyncService;

    @GetMapping("/account/history")
    public String test(HttpSession session, ModelMap model) throws Exception {
        session.setAttribute("user", 3L);
        Long userId = (Long) session.getAttribute("user");
//        model.addAttribute("transaction_history", asyncService.getTransactions(7L));
        asyncService.selectUserTransaction(userId, model);

        return "hello";
    }

    @GetMapping("/test")
    public String test2(ModelMap model){
        List<UserTransactionHistory> transactions = (List<UserTransactionHistory>) model.getAttribute("transaction");
        transactions.forEach(System.out::println);
        return "hi";
    }

}
