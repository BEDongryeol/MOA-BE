package com.moa.saving.goal.controller;

import com.moa.saving.goal.vo.UserGoal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saving/goal/")
public class MoaGoalController {

    // 목표 추가 진행이 되었을때
    @PutMapping("/save")
    public void saveGoal(@RequestBody UserGoal userGoal) {

    }

    // 목표명 수정 -> 목표명, 목표 종류, 목표 날짜
    @PutMapping("/update")
    public void updateGoal(@RequestBody UserGoal userGoal) {

    }

    // 목표명 해지(삭제)
    @DeleteMapping("/delete")
    public void deleteGoal(@RequestBody UserGoal userGoal) {

    }
}
