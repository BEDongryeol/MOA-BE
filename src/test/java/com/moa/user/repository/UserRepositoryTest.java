package com.moa.user.repository;

import com.moa.user.vo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
        user.setBirthDate(LocalDate.of(1997,9,3));

        User newUser = userRepository.save(user);
        System.out.println(newUser);

    }

}