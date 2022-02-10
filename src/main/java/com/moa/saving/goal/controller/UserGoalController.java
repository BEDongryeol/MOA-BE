package com.moa.saving.goal.controller;

import com.moa.saving.goal.service.UserGoalService;
import com.moa.saving.goal.vo.UserGoal;
import com.moa.user.repository.UserRepository;
import com.moa.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saving/goal/")
public class UserGoalController {

    private final UserGoalService userGoalService;

    // 전체 모으기 리스트
    @GetMapping("/all")
    public List<UserGoal> getAllList() {
        List<UserGoal> userGoalList = this.userGoalService.getAllList();
        return userGoalList;
    }
}
