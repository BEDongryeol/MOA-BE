package com.moa.user.controller;

import com.moa.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moa")
public class UserController {

    private final UserService userService;

    //생성자 연결
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    //회원가입
    @GetMapping("/users/join")
    public String createForm() {
        return "users/createUsersForm";
    }


}
