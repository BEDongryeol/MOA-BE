package com.moa.save.goal.controller;

import com.moa.challenge.moachallenge.vo.MoaChallenge;
import com.moa.save.goal.vo.UserGoal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/moa/goal/save/")
public class MoaGoalController {

    // 목표 저장 진행이 되었을때
    @GetMapping("/setgoal")
    public void setGoal(@RequestBody UserGoal userGoal) {


    }

    // 사용자가 모으기 탭 눌렀을때 보여지는 페이지
    @GetMapping("/getsavelist")
    public void getSaveList() {

    }
}
