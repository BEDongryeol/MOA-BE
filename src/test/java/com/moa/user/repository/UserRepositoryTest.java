package com.moa.user.repository;

import com.moa.finance.repository.AccountRepository;
import com.moa.finance.vo.dummy.TransactionHistory;
import com.moa.finance.vo.finance.Account;
import com.moa.user.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void userCreateTest(){
        User user = new User();
        user.setName("user1");
        user.setLoginId("");
        user.setPw("");
        user.setPhoneNum("010-1111-2222");
        user.setBirthDate("1997-09-03");

        User newUser = userRepository.save(user);
        System.out.println(newUser);

    }

}