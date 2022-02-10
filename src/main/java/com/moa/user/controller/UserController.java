package com.moa.user.controller;

import com.moa.user.service.UserService;
import com.moa.user.vo.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/moa")
public class UserController {

    private final UserService userService;


//    //회원가입
//    ///회원가입페이지
//    @GetMapping("/join")
//    public String join() {
//        return "/join";
//    }
//    ///회원가입처리
//    @PostMapping("/join")
//    public String joinUser(UserDto userDto) {
//    userService.joinUser(userDto);
//
//    return"redirect:/login";
//

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }


}
